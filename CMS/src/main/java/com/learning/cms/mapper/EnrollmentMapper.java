package com.learning.cms.mapper;

import com.learning.cms.dto.response.EnrollmentResponseDTO;
import com.learning.cms.entity.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public EnrollmentResponseDTO toResponseDTO(Enrollment enrollment) {
        return EnrollmentResponseDTO.builder()
                .id(enrollment.getId())
                .courseTitle(enrollment.getCourse().getTitle())
                .studentName(enrollment.getStudent().getFullName())
                .status(enrollment.getStatus())
                .progressPercentage(enrollment.getProgressPercentage())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .build();
    }
}
