package org.example.todo.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.todo.dto.AddToDoRequestDto;
import org.example.todo.dto.ToDoCreate;
import org.example.todo.dto.ToDoDto;

import org.example.todo.entity.ToDo;
import org.example.todo.entity.ToUser;
import org.example.todo.repository.ToDoRepository;
import org.example.todo.repository.UserRepository;
import org.example.todo.service.ToDoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoServiceimpl implements ToDoService {

    private final ToDoRepository toDoRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    @Override
    public ToDoDto createnewToDo(AddToDoRequestDto newtoDo) {
        ToDo newtodo= modelMapper.map(newtoDo, ToDo.class);
        ToDo toDo = toDoRepository.save(newtodo);
        return modelMapper.map(toDo, ToDoDto.class);
    }

    @Override
    public List<ToDoDto> getAllToDo() {
        List<ToDo> toDos = toDoRepository.findAll();
        return toDos.stream().map(ToDo -> modelMapper.map(ToDo,ToDoDto.class))
                .toList();
    }

    @Override
    public ToDoDto getByIdToDo(Long id) {
    ToDo toDo=toDoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("todo no found with id " +id));
        return modelMapper.map(toDo, ToDoDto.class);
    }

    @Override
    public void deletetodobyid(Long id) {
        if(!toDoRepository.existsById(id)){
            throw new IllegalArgumentException("To Do does not exists by "+id);
        }
        toDoRepository.deleteById(id);
    }

    @Override
    public ToDoDto updatetodocomplete(Long id, AddToDoRequestDto updatetodo) {
        ToDo toDo=toDoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("todo no found with id " +id));
         modelMapper.map(updatetodo, toDo);

        toDo = toDoRepository.save(toDo);
        return modelMapper.map(toDo, ToDoDto.class);
    }

    @Override
    public List<ToDoDto> searchByDescription(String search){
        List<ToDo> toDo = toDoRepository.searchOnDescription(search);
        return toDo.stream().map( ToDo ->modelMapper.map(ToDo, ToDoDto.class)).toList();

    // postman post { "search" : "nothing" } ->
        // controller searchByDescription (ToGetDsp searchRep)
        // -> service

    }

    @Override
    public List<ToDoDto> searchallByKeyword(String keyword) {
        List<ToDo> toDo = toDoRepository.searchallByKeyword(keyword);
        return toDo.stream().map( ToDo ->modelMapper.map(ToDo, ToDoDto.class)).toList();
    }


    @Transactional
    public AddToDoRequestDto createNewUserToDo(ToDoCreate toDoCreate) {
        Long userId = toDoCreate.getTouser_id();
        System.out.println("Step 1  "+ userId);

        ToUser user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("user not found with Id" + userId));
        System.out.println(user);
        ToDo todo = ToDo.builder()
                .title(toDoCreate.getTitle())
                .description(toDoCreate.getDescription())
                .build();

        System.out.println(todo);

        todo.setTouser(user);
        user.getToDos().add(todo);

        todo = toDoRepository.save(todo);
        return modelMapper.map(todo,AddToDoRequestDto.class);

    }
}
