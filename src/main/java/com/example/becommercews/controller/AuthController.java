package com.example.becommercews.controller;

import com.example.becommercews.dto.LoginDto;
import com.example.becommercews.dto.UserDto;
import com.example.becommercews.entity.User;
import com.example.becommercews.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public UserDto signup(@RequestBody User user){
        User newUser = authenticationService.register(user.getName(), user.getEmail(), user.getPassword(), user.getRole().getName());
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    public UserDto login(@RequestBody LoginDto loginDto){
        return authenticationService.login(loginDto);
    }
}
