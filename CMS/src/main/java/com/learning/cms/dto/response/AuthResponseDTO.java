package com.learning.cms.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {
    private String token;
    private String type;
    private Long userId;
    private String email;
    private String role;
}
