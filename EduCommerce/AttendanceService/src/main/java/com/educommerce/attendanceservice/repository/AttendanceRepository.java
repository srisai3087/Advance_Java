package com.educommerce.attendanceservice.repository;

import com.educommerce.attendanceservice.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentId(Long studentId);
    List<Attendance> findByCourseId(Long courseId);
    // Count present days for a student
    long countByStudentIdAndStatus(Long studentId, String status);
    // Count total days for a student
    long countByStudentId(Long studentId);
}
