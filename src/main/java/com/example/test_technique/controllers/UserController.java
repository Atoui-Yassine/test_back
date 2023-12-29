package com.example.test_technique.controllers;

import com.example.test_technique.DTOs.response.CoursDto;
import com.example.test_technique.services.cours.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class UserController {

    @Autowired
    CoursService coursService;

    @GetMapping("/cours")
    public ResponseEntity<List<CoursDto>> allCours(){
        return ResponseEntity.ok().body(coursService.findAll());

    }
}
