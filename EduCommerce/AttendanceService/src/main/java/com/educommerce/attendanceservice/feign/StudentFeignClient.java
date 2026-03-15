package com.educommerce.attendanceservice.feign;

import com.educommerce.attendanceservice.dto.StudentResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Calls Student Service to verify student exists before marking attendance
@FeignClient(name = "STUDENT-SERVICE")
public interface StudentFeignClient {
    @GetMapping("/students/{id}")
    ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable("id") Long studentId);
}
