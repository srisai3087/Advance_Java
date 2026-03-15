package com.educommerce.studentservice.service;

import com.educommerce.studentservice.dto.StudentResponseDTO;
import com.educommerce.studentservice.entity.Student;
import com.educommerce.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        return toResponseDTO(student);
    }

    public StudentResponseDTO updateStudent(Long id, StudentResponseDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        student.setName(dto.getName());
        student.setDepartment(dto.getDepartment());
        student.setSemester(dto.getSemester());
        return toResponseDTO(studentRepository.save(student));
    }

    public String deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
        return "Student with ID " + id + " deleted successfully";
    }

    // Convert Entity to ResponseDTO (password is never included)
    private StudentResponseDTO toResponseDTO(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDepartment(),
                student.getSemester(),
                student.getCreatedAt()
        );
    }

}
