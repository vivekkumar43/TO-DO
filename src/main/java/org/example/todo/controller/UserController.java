package org.example.todo.controller;

import lombok.RequiredArgsConstructor;
import org.example.todo.dto.AddToDoRequestDto;
import org.example.todo.dto.ToDoCreate;
import org.example.todo.dto.Userd.AddUser;
import org.example.todo.dto.Userd.UserDto;
import org.example.todo.service.ToDoService;
import org.example.todo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ToDoService toDoService;


    @GetMapping
    public List<UserDto> getallUser(){
        return userService.getAllUser();
    }

    @PostMapping
    public UserDto addUser(@RequestBody AddUser userAdd){
        return userService.addUser(userAdd);
    }

    @DeleteMapping("/{id}")
    public void delteuser(@PathVariable Long id){
    userService.delteuser(id);
    }

    @PutMapping("/{id}")
    public UserDto updateuser(@PathVariable Long id, @RequestBody AddUser updateUser){
        return userService.updateuser(id, updateUser);
    }

    @PatchMapping("/{id}")
    public UserDto partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object>updates){
    return userService.partialUpdate(id, updates);
    }


    @PostMapping("/new")
    public AddToDoRequestDto createNewUserToDo(@RequestBody  ToDoCreate toDoCreate){
        return toDoService.createNewUserToDo(toDoCreate);
    }





}