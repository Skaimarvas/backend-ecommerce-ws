package com.example.becommercews.service;

import com.example.becommercews.dto.UserDto;

import java.util.List;

public interface UserService {
    void findUserByEmail(String email);
    UserDto findUserById(long id);
    List<UserDto> findAll();
    UserDto remove(long id);

}
