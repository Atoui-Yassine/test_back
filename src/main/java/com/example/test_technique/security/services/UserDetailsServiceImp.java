package com.example.test_technique.security.services;

import com.example.test_technique.models.user.UserModel;
import com.example.test_technique.repositories.userR.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user=userRepository.findByUsernameOrEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found with username or email "+username));

        return UserDetailsImpl.build(user);
    }
}
