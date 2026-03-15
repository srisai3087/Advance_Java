package com.educommerce.studentservice.controller;

import com.educommerce.studentservice.dto.EnrollmentRequestDTO;
import com.educommerce.studentservice.dto.EnrollmentResponseDTO;
import com.educommerce.studentservice.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enroll")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // POST /enroll
    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> enrollStudent(@RequestBody EnrollmentRequestDTO dto) {
        return new ResponseEntity<>(enrollmentService.enrollStudent(dto), HttpStatus.CREATED);
    }

    // GET /students/{id}/courses
    @GetMapping("/students/{id}/courses")
    public ResponseEntity<List<EnrollmentResponseDTO>> getCoursesByStudent(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getCoursesByStudent(id));
    }

}
