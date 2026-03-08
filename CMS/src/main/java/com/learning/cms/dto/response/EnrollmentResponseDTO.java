package com.learning.cms.dto.response;

import com.learning.cms.entity.Enrollment;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EnrollmentResponseDTO {
    private Long id;
    private String courseTitle;
    private String studentName;
    private Enrollment.EnrollmentStatus status;
    private Double progressPercentage;
    private LocalDateTime enrollmentDate;
}
