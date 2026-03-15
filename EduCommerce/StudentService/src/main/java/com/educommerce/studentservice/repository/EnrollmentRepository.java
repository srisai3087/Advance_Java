package com.educommerce.studentservice.repository;

import com.educommerce.studentservice.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // Get all courses a student is enrolled in
    List<Enrollment> findByStudentId(Long studentId);
}
