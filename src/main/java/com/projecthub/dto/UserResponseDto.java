package com.projecthub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;

    private String username;

    private String email;

    private String avatarUrl;

    private String description;

    private List<UserProfileProjectDto> ownProjects;

    private List<UserProfileProjectDto> joinedProjects;

}
