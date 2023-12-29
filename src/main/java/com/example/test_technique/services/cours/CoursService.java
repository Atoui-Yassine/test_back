package com.example.test_technique.services.cours;

import com.example.test_technique.DTOs.response.CoursDto;
import com.example.test_technique.converter.CoursConvert;
import com.example.test_technique.exceptions.EntityNotFoundException;
import com.example.test_technique.models.cours.CoursModel;
import com.example.test_technique.repositories.coursR.CoursRepository;
import com.example.test_technique.services.user.UserService;
import com.example.test_technique.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoursService {

    @Autowired
    private CoursRepository coursRepository;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private UserService userService;
    public CoursDto findById(Long id){
        CoursModel c=coursRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException("cours not found with id : "+id)
        );
        return CoursConvert.convertToDto(c);
    }
    public List<CoursDto> findAll(){
        return coursRepository.findAll()
                .stream()
                .map(CoursConvert::convertToDto)
                .collect(Collectors.toList());
    }
    public CoursDto save(CoursModel coursModel) throws Exception{
        coursModel.setUserF(userService.findById(tokenUtils.ExtractId()));
       CoursModel coursModel1=coursRepository.save(coursModel);
        return CoursConvert.convertToDto(coursModel1);
    }

    public CoursDto update(Long id, CoursModel c){
        CoursModel cours=coursRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException(" cours not found with id : "+id)
        );
        cours.setPrice(c.getPrice());
        cours.setTitle(c.getTitle());
        CoursModel coursModel=coursRepository.save(cours);
       return CoursConvert.convertToDto(coursModel);
    }


    public void deleteById(Long id) {
        coursRepository.deleteById(id);
    }


}
