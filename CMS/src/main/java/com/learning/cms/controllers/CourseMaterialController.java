package com.learning.cms.controllers;

import com.learning.cms.dto.response.MaterialResponseDTO;
import com.learning.cms.service.CourseMaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
@Tag(name = "Course Materials", description = "Upload and download course materials")
public class CourseMaterialController {

    private final CourseMaterialService materialService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload a course material")
    public ResponseEntity<MaterialResponseDTO> upload(
            @RequestParam String title,
            @RequestParam Long courseId,
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(materialService.uploadMaterial(title, courseId, file));
    }

    @GetMapping("/{id}/download")
    @Operation(summary = "Download a course material")
    public ResponseEntity<Resource> download(@PathVariable Long id) {
        Resource resource = materialService.downloadMaterial(id);
        String fileName = materialService.getFileName(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Get all materials for a course")
    public ResponseEntity<List<MaterialResponseDTO>> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(materialService.getMaterialsByCourse(courseId));
    }
}
