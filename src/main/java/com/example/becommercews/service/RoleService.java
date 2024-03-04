package com.example.becommercews.service;

import com.example.becommercews.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();
    Role find(long id);
    Role save(Role role);
    Role remove(long id);

}
