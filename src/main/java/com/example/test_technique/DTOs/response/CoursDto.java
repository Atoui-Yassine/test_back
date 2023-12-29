package com.example.test_technique.DTOs.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CoursDto {
    private Long id;
    private String title;
    private double price;
    private String file;
    private UserDto userFDto;
}
