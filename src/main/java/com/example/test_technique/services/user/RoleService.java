package com.example.test_technique.services.user;

import com.example.test_technique.exceptions.EntityNotFoundException;
import com.example.test_technique.models.user.ERole;
import com.example.test_technique.models.user.Role;
import com.example.test_technique.repositories.userR.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findRoleByName(ERole name){
        Role role=roleRepository.findRoleByName(name)
                .orElseThrow(()->new EntityNotFoundException("role not found with name :"+name));
        return role;
    }
}
