package com.educommerce.resultservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponseDTO {
    private Long id;
    private Long studentId;
    private Long courseId;
    private String examType;
    private Double marksObtained;
    private Double maxMarks;
    private String grade;
    // Attendance percentage fetched from Attendance Service via Feign
    private Double attendancePercentage;
}
