package com.learning.cms.service;

import com.learning.cms.dto.response.MaterialResponseDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseMaterialService {
    MaterialResponseDTO uploadMaterial(String title, Long courseId, MultipartFile file);
    Resource downloadMaterial(Long materialId);
    List<MaterialResponseDTO> getMaterialsByCourse(Long courseId);
    String getFileName(Long materialId);
}
