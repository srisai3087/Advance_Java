package com.learning.cms.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CourseResponseDTO {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String duration;
    private String level;
    private String instructorName;
    private LocalDateTime createdAt;
}
