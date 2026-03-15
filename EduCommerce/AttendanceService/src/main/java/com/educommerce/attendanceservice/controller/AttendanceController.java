package com.educommerce.attendanceservice.controller;

import com.educommerce.attendanceservice.dto.AttendanceRequestDTO;
import com.educommerce.attendanceservice.dto.AttendanceResponseDTO;
import com.educommerce.attendanceservice.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // POST /attendance - mark attendance
    @PostMapping
    public ResponseEntity<AttendanceResponseDTO> markAttendance(@RequestBody AttendanceRequestDTO dto) {
        return new ResponseEntity<>(attendanceService.markAttendance(dto), HttpStatus.CREATED);
    }

    // GET /attendance/student/{studentId}
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceResponseDTO>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByStudent(studentId));
    }

    // GET /attendance/course/{courseId}
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<AttendanceResponseDTO>> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByCourse(courseId));
    }

    // PUT /attendance/{id}
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResponseDTO> updateAttendance(@PathVariable Long id, @RequestBody AttendanceRequestDTO dto) {
        return ResponseEntity.ok(attendanceService.updateAttendance(id, dto));
    }

    // DELETE /attendance/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long id) {
        return ResponseEntity.ok(attendanceService.deleteAttendance(id));
    }

    // GET /attendance/percentage/{studentId} - called by Result Service via Feign
    @GetMapping("/percentage/{studentId}")
    public ResponseEntity<Double> getAttendancePercentage(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.getAttendancePercentage(studentId));
    }

}
