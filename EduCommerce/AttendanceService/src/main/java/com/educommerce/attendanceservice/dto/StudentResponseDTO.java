package com.educommerce.attendanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Copy of Student Service's StudentResponseDTO
// Feign Client needs this to deserialize the response from Student Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String department;
    private Integer semester;
}
