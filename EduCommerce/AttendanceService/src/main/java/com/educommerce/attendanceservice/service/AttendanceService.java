package com.educommerce.attendanceservice.service;

import com.educommerce.attendanceservice.dto.AttendanceRequestDTO;
import com.educommerce.attendanceservice.dto.AttendanceResponseDTO;
import com.educommerce.attendanceservice.entity.Attendance;
import com.educommerce.attendanceservice.feign.StudentFeignClient;
import com.educommerce.attendanceservice.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // Feign Client to verify student exists in Student Service
    @Autowired
    private StudentFeignClient studentFeignClient;

    // ==================== MARK ATTENDANCE ====================
    public AttendanceResponseDTO markAttendance(AttendanceRequestDTO dto) {
        // Step 1: Verify student exists by calling Student Service
        var studentResponse = studentFeignClient.getStudentById(dto.getStudentId());
        if (studentResponse.getBody() == null) {
            throw new RuntimeException("Student not found with ID: " + dto.getStudentId());
        }

        // Step 2: Save attendance record
        Attendance attendance = new Attendance();
        attendance.setStudentId(dto.getStudentId());
        attendance.setCourseId(dto.getCourseId());
        attendance.setDate(dto.getDate());
        attendance.setStatus(dto.getStatus());

        Attendance saved = attendanceRepository.save(attendance);
        return toResponseDTO(saved);
    }

    // ==================== GET BY STUDENT ====================
    public List<AttendanceResponseDTO> getAttendanceByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId)
                .stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    // ==================== GET BY COURSE ====================
    public List<AttendanceResponseDTO> getAttendanceByCourse(Long courseId) {
        return attendanceRepository.findByCourseId(courseId)
                .stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    // ==================== UPDATE ====================
    public AttendanceResponseDTO updateAttendance(Long id, AttendanceRequestDTO dto) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance record not found with ID: " + id));
        attendance.setStatus(dto.getStatus());
        attendance.setDate(dto.getDate());
        return toResponseDTO(attendanceRepository.save(attendance));
    }

    // ==================== DELETE ====================
    public String deleteAttendance(Long id) {
        if (!attendanceRepository.existsById(id)) {
            throw new RuntimeException("Attendance record not found with ID: " + id);
        }
        attendanceRepository.deleteById(id);
        return "Attendance record with ID " + id + " deleted successfully";
    }

    // ==================== ATTENDANCE PERCENTAGE ====================
    // Called by Result Service via Feign to calculate attendance %
    public double getAttendancePercentage(Long studentId) {
        long total = attendanceRepository.countByStudentId(studentId);
        if (total == 0) return 0.0;
        long present = attendanceRepository.countByStudentIdAndStatus(studentId, "Present");
        return ((double) present / total) * 100;
    }

    private AttendanceResponseDTO toResponseDTO(Attendance attendance) {
        return new AttendanceResponseDTO(
                attendance.getId(),
                attendance.getStudentId(),
                attendance.getCourseId(),
                attendance.getDate(),
                attendance.getStatus()
        );
    }

}
