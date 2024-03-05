package com.example.becommercews.controller;

import com.example.becommercews.dto.LoginDto;
import com.example.becommercews.dto.SignupDto;
import com.example.becommercews.dto.UserDto;
import com.example.becommercews.entity.Store;
import com.example.becommercews.entity.User;
import com.example.becommercews.service.AuthenticationService;
import com.example.becommercews.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final StoreService storeService;

    @Autowired
    public AuthController(AuthenticationService authenticationService,StoreService storeService){
        this.authenticationService = authenticationService;
        this.storeService = storeService;
    }

    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignupDto signupDto){
        User newUser = authenticationService.register(signupDto.getName(), signupDto.getEmail(), signupDto.getPassword(), signupDto.getRoleId());
        Store store = signupDto.getStore();
        store.setUser(newUser);
        store.getUser().setId(newUser.getId());
        Store newStore =  storeService.save(store);
        newUser.setStore(store);
        newUser.getStore().setId(newStore.getId());
        return new UserDto(newUser.getId(), newUser.getName(), newUser.getEmail());
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody LoginDto loginDto){
        return authenticationService.login(loginDto);
    }
}
