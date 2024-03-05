package com.example.becommercews.service;

import com.example.becommercews.dto.UserDto;
import com.example.becommercews.entity.User;

import java.util.List;

public interface UserService {
    void findUserByEmail(String email);
    UserDto findUserById(long id);
    UserDto save(User user);
    List<UserDto> findAll();
    UserDto remove(long id);

}
