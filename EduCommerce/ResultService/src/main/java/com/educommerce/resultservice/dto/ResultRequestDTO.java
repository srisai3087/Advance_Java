package com.educommerce.resultservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultRequestDTO {
    private Long studentId;
    private Long courseId;
    private String examType;
    private Double marksObtained;
    private Double maxMarks;
    private String grade;
}
