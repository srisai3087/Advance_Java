package com.learning.cms.mapper;

import com.learning.cms.dto.response.MaterialResponseDTO;
import com.learning.cms.entity.CourseMaterial;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {

    public MaterialResponseDTO toResponseDTO(CourseMaterial material) {
        return MaterialResponseDTO.builder()
                .id(material.getId())
                .title(material.getTitle())
                .fileName(material.getFileName())
                .fileType(material.getFileType())
                .fileUrl(material.getFileUrl())
                .uploadDate(material.getUploadDate())
                .build();
    }
}
