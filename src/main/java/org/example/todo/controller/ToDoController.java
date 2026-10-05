package org.example.todo.controller;


import lombok.RequiredArgsConstructor;
import org.example.todo.dto.AddToDoRequestDto;

import org.example.todo.dto.AllMatching;
import org.example.todo.dto.ToDoDto;

import org.example.todo.entity.ToDo;
import org.example.todo.repository.ToDoRepository;
import org.example.todo.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ToDo")
@RequiredArgsConstructor

public class ToDoController {

private final ToDoService toDoService;

    @GetMapping
    public List<ToDoDto> getToDo(){
        return toDoService.getAllToDo();
    }


    @GetMapping("/{id}")
    public ToDoDto getById(@PathVariable Long id){
        return toDoService.getByIdToDo(id);
    }


    @PostMapping
    public ResponseEntity<ToDoDto> createnewToDo(@RequestBody AddToDoRequestDto newtoDo){
        return ResponseEntity.status(HttpStatus.CREATED).body(toDoService.createnewToDo(newtoDo));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletetodo (@PathVariable Long id){
        toDoService.deletetodobyid(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<ToDoDto>  updatetodocomplete(@PathVariable Long id, @RequestBody AddToDoRequestDto updatetodo){
        return ResponseEntity.ok(toDoService.updatetodocomplete(id, updatetodo));
    }

    @GetMapping("/search/{searchString}")
    public List<ToDoDto> searchbyDescription(@PathVariable String searchString){
        return toDoService.searchByDescription(searchString);
    }
    @PostMapping("/search/all")
    public List<ToDoDto> searchallByKeyword(@RequestBody AllMatching keyword){
        return toDoService.searchallByKeyword(keyword.getKeyword());
    }
    // @PostMapping("/search/{")









}
