package com.example.test_technique.utils;

import com.example.test_technique.security.services.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TokenUtils {
    public TokenUtils() {
    }

    public Long ExtractId(){
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return userDetailsImpl.getId();
    }
}
