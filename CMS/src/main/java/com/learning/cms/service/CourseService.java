package com.learning.cms.service;

import com.learning.cms.dto.request.CourseRequestDTO;
import com.learning.cms.dto.response.CourseResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    CourseResponseDTO createCourse(CourseRequestDTO request);
    CourseResponseDTO updateCourse(Long id, CourseRequestDTO request);
    void deleteCourse(Long id);
    Page<CourseResponseDTO> getAllCourses(Pageable pageable);
    CourseResponseDTO getCourseById(Long id);
}
