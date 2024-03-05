package com.example.becommercews.dto;

import com.example.becommercews.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupDto {
    private String name;
    private String email;
    private String password;
    private long roleId;
    private Store store;
}
