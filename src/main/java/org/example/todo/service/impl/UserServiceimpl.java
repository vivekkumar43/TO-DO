package org.example.todo.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.todo.dto.Userd.AddUser;
import org.example.todo.dto.Userd.UserDto;
import org.example.todo.entity.ToUser;
import org.example.todo.repository.UserRepository;
import org.example.todo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceimpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<UserDto> getAllUser(){
        List<ToUser> toUsers = userRepository.findAll();
        return toUsers.stream().map(ToUser -> modelMapper.map(ToUser, UserDto.class)).toList();
    }

    @Override
    public UserDto addUser(AddUser userAdd) {
        ToUser newToUser = modelMapper.map(userAdd, ToUser.class);
        ToUser toUserDto =userRepository.save(newToUser);
        return modelMapper.map(toUserDto,UserDto.class);
    }

    @Override
    public void delteuser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateuser(Long id, AddUser updateUser) {

        ToUser toUser = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("user not found with "+id));
        modelMapper.map(updateUser, ToUser.class);
        toUser = userRepository.save(toUser);
        return modelMapper.map(toUser, UserDto.class);
    }

    @Override
    public UserDto partialUpdate(Long id, Map<String, Object> updates){
        ToUser toUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("user not present with "+ id));
        updates.forEach((field, value) ->{
            switch (field){
                case "name":
                    toUser.setName((String) value);
                    break;

                case "email":
                    toUser.setEmail((String) value);
                    break;
                case "password":
                    toUser.setPassword((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("method not supported");
            }
        });

        ToUser updateduser = userRepository.save(toUser);
        return modelMapper.map(updateduser, UserDto.class);
    }

}
