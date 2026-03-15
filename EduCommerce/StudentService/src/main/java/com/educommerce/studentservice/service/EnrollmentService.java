package com.educommerce.studentservice.service;

import com.educommerce.studentservice.dto.EnrollmentRequestDTO;
import com.educommerce.studentservice.dto.EnrollmentResponseDTO;
import com.educommerce.studentservice.entity.Enrollment;
import com.educommerce.studentservice.repository.CourseRepository;
import com.educommerce.studentservice.repository.EnrollmentRepository;
import com.educommerce.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public EnrollmentResponseDTO enrollStudent(EnrollmentRequestDTO dto) {
        // Validate student exists
        if (!studentRepository.existsById(dto.getStudentId())) {
            throw new RuntimeException("Student not found with ID: " + dto.getStudentId());
        }
        // Validate course exists
        if (!courseRepository.existsById(dto.getCourseId())) {
            throw new RuntimeException("Course not found with ID: " + dto.getCourseId());
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(dto.getStudentId());
        enrollment.setCourseId(dto.getCourseId());
        enrollment.setEnrollmentDate(new Date());

        Enrollment saved = enrollmentRepository.save(enrollment);
        return new EnrollmentResponseDTO(saved.getId(), saved.getStudentId(), saved.getCourseId(), saved.getEnrollmentDate());
    }

    public List<EnrollmentResponseDTO> getCoursesByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId).stream()
                .map(e -> new EnrollmentResponseDTO(e.getId(), e.getStudentId(), e.getCourseId(), e.getEnrollmentDate()))
                .collect(Collectors.toList());
    }

}
