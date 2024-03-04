package com.example.becommercews.controller;


import com.example.becommercews.entity.Role;
import com.example.becommercews.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/api/role")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public List<Role> findAll(){
        return roleService.findAll();
    }


    @PostMapping
    public Role save(@RequestBody Role role){
        return roleService.save(role);
    }
}
