package com.learning.cms.dto.response;

import com.learning.cms.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String fullName;
    private String email;
    private User.Role role;
    private String profilePicture;
    private LocalDateTime createdAt;
}
