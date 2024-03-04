package com.example.becommercews.service;

import com.example.becommercews.entity.Role;
import com.example.becommercews.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role find(long id) {
        Optional<Role> foundRole = roleRepository.findById(id);
        if(foundRole.isPresent()){
            return foundRole.get();
        }
        return null;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role remove(long id) {
        Role role = find(id);
        if(role != null){
            roleRepository.delete(role);
            return role;
        }
        return null;
    }
}
