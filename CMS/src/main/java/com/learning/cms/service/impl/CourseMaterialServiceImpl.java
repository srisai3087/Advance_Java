package com.learning.cms.service.impl;

import com.learning.cms.dto.response.MaterialResponseDTO;
import com.learning.cms.entity.Course;
import com.learning.cms.entity.CourseMaterial;
import com.learning.cms.exception.FileStorageException;
import com.learning.cms.exception.ResourceNotFoundException;
import com.learning.cms.mapper.MaterialMapper;
import com.learning.cms.repository.CourseMaterialRepository;
import com.learning.cms.repository.CourseRepository;
import com.learning.cms.service.CourseMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseMaterialServiceImpl implements CourseMaterialService {

    private final CourseMaterialRepository materialRepository;
    private final CourseRepository courseRepository;
    private final MaterialMapper materialMapper;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public MaterialResponseDTO uploadMaterial(String title, Long courseId, MultipartFile file) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String storedFileName = UUID.randomUUID() + "_" + originalFileName;

        try {
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);
            Path targetLocation = uploadPath.resolve(storedFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + originalFileName, ex);
        }

        CourseMaterial material = CourseMaterial.builder()
                .title(title)
                .fileName(storedFileName)
                .fileType(file.getContentType())
                .fileUrl("/api/materials/" + storedFileName + "/download")
                .course(course)
                .build();

        return materialMapper.toResponseDTO(materialRepository.save(material));
    }

    @Override
    public Resource downloadMaterial(Long materialId) {
        CourseMaterial material = materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found with id: " + materialId));

        try {
            Path filePath = Paths.get(uploadDir).toAbsolutePath().normalize().resolve(material.getFileName());
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new FileStorageException("File not found: " + material.getFileName());
            }
        } catch (MalformedURLException ex) {
            throw new FileStorageException("File not found: " + material.getFileName(), ex);
        }
    }

    @Override
    public List<MaterialResponseDTO> getMaterialsByCourse(Long courseId) {
        return materialRepository.findByCourseId(courseId)
                .stream()
                .map(materialMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String getFileName(Long materialId) {
        return materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found with id: " + materialId))
                .getFileName();
    }
}
