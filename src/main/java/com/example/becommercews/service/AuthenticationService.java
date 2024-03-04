package com.example.becommercews.service;

import com.example.becommercews.dto.LoginDto;
import com.example.becommercews.dto.UserDto;
import com.example.becommercews.entity.Role;
import com.example.becommercews.entity.User;
import com.example.becommercews.exception.BecommerceErrorResponse;
import com.example.becommercews.exception.BecommerceException;
import com.example.becommercews.repository.RoleRepository;
import com.example.becommercews.repository.UserRepository;
import com.example.becommercews.validation.BecommerceValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User register(String name, String email, String password,String code){
        Role userRole = new Role();
        String encodedPassword = passwordEncoder.encode(password);
        Optional<Role> optionalRole = roleRepository.findByCode(code);
        if(optionalRole.isPresent()){
            userRole = optionalRole.get();
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(encodedPassword);
            userRepository.save(user);
            return user;
        } else {
            throw new BecommerceException("Role mustn't empty or match roles on our system", HttpStatus.BAD_REQUEST);
        }
    }

    public UserDto login (LoginDto loginDto){
        BecommerceValidation.checkEmptyOrNull(loginDto.email(),"email");
        BecommerceValidation.checkEmptyOrNull(loginDto.password(),"password");
        Optional<User> optionalUser = userRepository.findUserByEmail(loginDto.email());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            boolean isPasswordValid = passwordEncoder.matches(loginDto.password(), user.getPassword());
            if(isPasswordValid){
                return new UserDto(user.getId(), user.getName(), user.getEmail());
            } else {
                throw new BecommerceException("Invalid Credentials", HttpStatus.BAD_REQUEST);
            }
        }
        else {
            throw new BecommerceException("Invalid Credentials", HttpStatus.BAD_REQUEST);
        }
    }
}
