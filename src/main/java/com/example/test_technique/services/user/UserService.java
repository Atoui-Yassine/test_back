package com.example.test_technique.services.user;

import com.example.test_technique.exceptions.EntityNotFoundException;
import com.example.test_technique.models.user.UserModel;
import com.example.test_technique.repositories.userR.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserModel saveUser(UserModel user){
        return userRepository.save(user);
    }
    public Boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
    public Boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
    public UserModel findById(Long id){
        UserModel user=userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User not found with id : "+id));
        return user;
    }
    public UserModel findByUsername(String username){
        UserModel user=userRepository.findByUsernameOrEmail(username)
                .orElseThrow(()->new EntityNotFoundException("User not found By username : "+username  ));
        return user;
    }

}
