package com.learning.cms.mapper;

import com.learning.cms.dto.response.CourseResponseDTO;
import com.learning.cms.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseResponseDTO toResponseDTO(Course course) {
        return CourseResponseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .price(course.getPrice())
                .duration(course.getDuration())
                .level(course.getLevel())
                .instructorName(course.getInstructor().getFullName())
                .createdAt(course.getCreatedAt())
                .build();
    }
}
