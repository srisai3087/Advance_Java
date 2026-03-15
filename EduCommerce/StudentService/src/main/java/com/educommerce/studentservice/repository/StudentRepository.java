package com.educommerce.studentservice.repository;

import com.educommerce.studentservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Used for login - find student by email
    Optional<Student> findByEmail(String email);
    // Check if email already registered
    boolean existsByEmail(String email);
}
