package com.example.test_technique.converter;

import com.example.test_technique.DTOs.response.UserDto;
import com.example.test_technique.models.user.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConvert {
    public static UserDto convertToDto (UserModel userModel) {
        UserDto userDto=new UserDto();
        userDto.setId(userModel.getId());
        userDto.setFirstName(userModel.getFirstname());
        userDto.setLastName(userModel.getLastname());
        userDto.setUsername(userModel.getUsername());
        userDto.setEmail(userModel.getEmail());
        return  userDto;
    }
    public static UserModel convert (UserDto userDto) {
        UserModel userModel = new UserModel();
        userModel.setId(userDto.getId());
        userModel.setFirstname(userDto.getFirstName());
        userModel.setLastname(userDto.getLastName());
        userModel.setUsername(userDto.getUsername());
        userModel.setEmail(userDto.getEmail());
        return userModel;

    }
}
