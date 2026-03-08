package com.learning.cms.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MaterialResponseDTO {
    private Long id;
    private String title;
    private String fileName;
    private String fileType;
    private String fileUrl;
    private LocalDateTime uploadDate;
}
