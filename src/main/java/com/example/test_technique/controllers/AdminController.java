package com.example.test_technique.controllers;

import com.example.test_technique.DTOs.response.CoursDto;
import com.example.test_technique.converter.CoursConvert;
import com.example.test_technique.models.cours.CoursModel;
import com.example.test_technique.models.user.UserModel;
import com.example.test_technique.services.cours.CoursService;
import com.example.test_technique.services.flickr.FlickrService;
import com.example.test_technique.services.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.security.Principal;


@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Slf4j

public class AdminController {
    @Autowired
    CoursService coursService;
    @Autowired
    UserService userService;
    @Autowired
    FlickrService flickrService;


    @GetMapping("/cours/{id}")
    public ResponseEntity<CoursDto> returnCoursById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(coursService.findById(id));
    }

    @PostMapping("/cours/create")
    public ResponseEntity<CoursDto> createCours(@RequestBody CoursModel c) throws Exception {

        return ResponseEntity.ok().body(coursService.save(c));
    }
    @DeleteMapping("/cours/{id}")
    public ResponseEntity<String> deletecoursById(@PathVariable("id") Long id){
        coursService.deleteById(id);
        return ResponseEntity.ok().body("{\"Status\": \"Successful Deletion\"}");
    }
    @PutMapping("/cours/update/{id}")
    public ResponseEntity<?> updateCours(@PathVariable("id") Long id,@RequestBody CoursModel coursModel,Principal principal){
        UserModel user= userService.findByUsername(principal.getName());
        CoursDto cours=coursService.findById(id);
        if(coursModel != null && user.getId() == cours.getUserFDto().getId()){

            return ResponseEntity.ok().body(coursService.update(id,coursModel));
        }else
            return ResponseEntity.ok().body("{\"Status\": \"Cours not found\"}");
    }

    @PostMapping("/uploadPhotos/{id}")
    public ResponseEntity<String> uploadPhotos(
            @PathVariable Long id,
            MultipartFile coursPhoto,
            @RequestParam("title") String title,
            Principal principal

    ){
        try {
            CoursDto cours=coursService.findById(id);
            if(principal != null && cours != null){

                if( coursPhoto != null){
                    InputStream coursPhotoInputStream = coursPhoto.getInputStream();
                    String coursPhotoUrl = flickrService.savePhoto(coursPhotoInputStream, title);
                    cours.setFile(coursPhotoUrl);
                    coursService.save(CoursConvert.convert(cours));
                    return ResponseEntity.ok().body("Uploaded and updated cours photo URL: " + coursPhotoUrl);
                }

                else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Status\": \" Photo Not Existed \"}");
                }
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"Status\": \"  Not a user \"}");
            }

        }catch (Exception ex) {
            log.error("Error uploading and updating photos: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading and updating photos.");

        }

    }

}
