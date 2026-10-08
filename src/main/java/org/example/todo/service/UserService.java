package org.example.todo.service;

import org.example.todo.dto.Userd.AddUser;
import org.example.todo.dto.Userd.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<UserDto> getAllUser();

    UserDto addUser(AddUser userAdd);

    void delteuser(Long id);

    UserDto updateuser(Long id, AddUser updateUser);

    UserDto partialUpdate(Long id, Map<String, Object> updates);
}
