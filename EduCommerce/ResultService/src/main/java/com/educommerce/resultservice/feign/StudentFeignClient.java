package com.educommerce.resultservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;

// Calls Student Service to verify student details
@FeignClient(name = "STUDENT-SERVICE")
public interface StudentFeignClient {
    @GetMapping("/students/{id}")
    ResponseEntity<Map<String, Object>> getStudentById(@PathVariable("id") Long studentId);
}
