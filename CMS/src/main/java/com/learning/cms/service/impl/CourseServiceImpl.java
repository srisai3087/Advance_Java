package com.learning.cms.service.impl;

import com.learning.cms.dto.request.CourseRequestDTO;
import com.learning.cms.dto.response.CourseResponseDTO;
import com.learning.cms.entity.Course;
import com.learning.cms.entity.User;
import com.learning.cms.exception.ResourceNotFoundException;
import com.learning.cms.mapper.CourseMapper;
import com.learning.cms.repository.CourseRepository;
import com.learning.cms.repository.UserRepository;
import com.learning.cms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseMapper courseMapper;

    @Override
    @CacheEvict(value = "courses", allEntries = true)
    public CourseResponseDTO createCourse(CourseRequestDTO request) {
        User instructor = userRepository.findById(request.getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id: " + request.getInstructorId()));

        Course course = Course.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .duration(request.getDuration())
                .level(request.getLevel())
                .instructor(instructor)
                .build();

        return courseMapper.toResponseDTO(courseRepository.save(course));
    }

    @Override
    @CacheEvict(value = "courses", allEntries = true)
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setDuration(request.getDuration());
        course.setLevel(request.getLevel());

        return courseMapper.toResponseDTO(courseRepository.save(course));
    }

    @Override
    @CacheEvict(value = "courses", allEntries = true)
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "courses")
    public Page<CourseResponseDTO> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable).map(courseMapper::toResponseDTO);
    }

    @Override
    public CourseResponseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return courseMapper.toResponseDTO(course);
    }
}
