package com.example.test_technique.converter;

import com.example.test_technique.DTOs.response.CoursDto;
import com.example.test_technique.DTOs.response.UserDto;
import com.example.test_technique.models.cours.CoursModel;
import com.example.test_technique.models.user.UserModel;
import org.springframework.stereotype.Component;

@Component
public class CoursConvert {
    public static CoursDto convertToDto (CoursModel coursModel) {
        CoursDto coursDto=new CoursDto();
        coursDto.setId(coursModel.getId());
        coursDto.setTitle(coursModel.getTitle());
        coursDto.setPrice(coursModel.getPrice());
        coursDto.setFile(coursModel.getFile());
        coursDto.setUserFDto(UserConvert.convertToDto(coursModel.getUserF()));
        return  coursDto;
    }
    public static CoursModel convert (CoursDto coursDto) {
        CoursModel coursModel = new CoursModel();
        coursModel.setId(coursDto.getId());
        coursModel.setTitle(coursDto.getTitle());
        coursModel.setPrice(coursDto.getPrice());
        coursModel.setFile(coursDto.getFile());
        coursModel.setUserF(UserConvert.convert(coursDto.getUserFDto()));
        return coursModel;

    }
}
