package com.educommerce.studentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDTO {
    private String courseName;
    private String courseCode;
    private String instructor;
    private Integer credits;
}
