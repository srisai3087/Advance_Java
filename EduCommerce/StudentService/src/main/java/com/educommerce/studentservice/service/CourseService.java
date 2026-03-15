package com.educommerce.studentservice.service;

import com.educommerce.studentservice.dto.CourseRequestDTO;
import com.educommerce.studentservice.dto.CourseResponseDTO;
import com.educommerce.studentservice.entity.Course;
import com.educommerce.studentservice.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseResponseDTO createCourse(CourseRequestDTO dto) {
        Course course = new Course();
        course.setCourseName(dto.getCourseName());
        course.setCourseCode(dto.getCourseCode());
        course.setInstructor(dto.getInstructor());
        course.setCredits(dto.getCredits());
        return toResponseDTO(courseRepository.save(course));
    }

    public List<CourseResponseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));
        course.setCourseName(dto.getCourseName());
        course.setCourseCode(dto.getCourseCode());
        course.setInstructor(dto.getInstructor());
        course.setCredits(dto.getCredits());
        return toResponseDTO(courseRepository.save(course));
    }

    public String deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found with ID: " + id);
        }
        courseRepository.deleteById(id);
        return "Course with ID " + id + " deleted successfully";
    }

    private CourseResponseDTO toResponseDTO(Course course) {
        return new CourseResponseDTO(
                course.getId(),
                course.getCourseName(),
                course.getCourseCode(),
                course.getInstructor(),
                course.getCredits()
        );
    }

}
