package org.example.todo.service;

import org.example.todo.dto.AddToDoRequestDto;
import org.example.todo.dto.AllMatching;
import org.example.todo.dto.ToDoDto;

import java.util.List;

public interface ToDoService {

    ToDoDto createnewToDo(AddToDoRequestDto newtoDo);


    List<ToDoDto> getAllToDo();

    ToDoDto getByIdToDo(Long id);

    void deletetodobyid(Long id);

    ToDoDto updatetodocomplete(Long id, AddToDoRequestDto updatetodo);

    List<ToDoDto> searchByDescription(String search);

    List<ToDoDto> searchallByKeyword(String keyword);


}
