package com.educommerce.studentservice.service;

import com.educommerce.studentservice.dto.*;
import com.educommerce.studentservice.entity.Student;
import com.educommerce.studentservice.repository.StudentRepository;
import com.educommerce.studentservice.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ==================== REGISTER ====================
    public AuthResponseDTO register(RegisterRequestDTO requestDTO) {
        // Check if email already exists
        if (studentRepository.existsByEmail(requestDTO.getEmail())) {
            throw new RuntimeException("Email already registered: " + requestDTO.getEmail());
        }

        // Create new student entity
        Student student = new Student();
        student.setName(requestDTO.getName());
        student.setEmail(requestDTO.getEmail());
        // Encode password with BCrypt before saving to database
        student.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        student.setDepartment(requestDTO.getDepartment());
        student.setSemester(requestDTO.getSemester());

        studentRepository.save(student);

        // Generate JWT token for the new student
        String token = jwtUtil.generateToken(student.getEmail());

        return new AuthResponseDTO(token, student.getEmail(), student.getName(), "Registration successful");
    }


    public AuthResponseDTO login(LoginRequestDTO requestDTO) {
        // Find student by email
        Student student = studentRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Student not found with email: " + requestDTO.getEmail()));

        // Check if password matches the stored hash
        if (!passwordEncoder.matches(requestDTO.getPassword(), student.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(student.getEmail());

        return new AuthResponseDTO(token, student.getEmail(), student.getName(), "Login successful");
    }

}
