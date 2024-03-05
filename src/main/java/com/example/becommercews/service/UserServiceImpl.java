package com.example.becommercews.service;

import com.example.becommercews.dto.UserDto;
import com.example.becommercews.entity.Store;
import com.example.becommercews.entity.User;
import com.example.becommercews.exception.BecommerceException;
import com.example.becommercews.repository.StoreRepository;
import com.example.becommercews.repository.UserRepository;
import com.example.becommercews.validation.BecommerceValidation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService,UserService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, StoreRepository storeRepository){
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
    }


    @Override
    public void findUserByEmail(String email) {
        boolean isUserExist = userRepository.findUserByEmail(email).isPresent();
        if(isUserExist){
            throw new BecommerceException(BecommerceValidation.IS_EMAIL_PRESENT, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public UserDto findUserById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return new UserDto(user.getId(), user.getName(), user.getEmail() );

        } else {
            throw new BecommerceException(BecommerceValidation.IS_USER_PRESENT,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for(User user: users){
            userDtos.add(new UserDto(user.getId(), user.getName(), user.getEmail()));
        }
        return userDtos;
    }

    @Override
    public UserDto save(User user) {
        BecommerceValidation.checkEmptyOrNull(user.getName(),"name");
        BecommerceValidation.checkEmptyOrNull(user.getEmail(),"email");
        BecommerceValidation.checkEmptyOrNull(user.getPassword(),"password");
        User userSaved = userRepository.save(user);
        return new UserDto(userSaved.getId(), userSaved.getName(), userSaved.getEmail());
    }

    @Override
    public UserDto remove(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User userFound = user.get();
            userRepository.delete(userFound);
            return new UserDto(userFound.getId(), userFound.getName(), userFound.getEmail());
        } else {
            throw new BecommerceException(BecommerceValidation.IS_USER_PRESENT, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findUserByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException(BecommerceValidation.IS_USER_VALID));
    }
}
