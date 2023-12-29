package com.example.test_technique.DTOs.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Data
public class SignupRequest {
    @NotNull
    private  String firstname;
    @NotNull
    private  String lastname;
    @NotNull
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Length(min = 3, max = 15)
    private String password;

    private Set<String> roles;
}
