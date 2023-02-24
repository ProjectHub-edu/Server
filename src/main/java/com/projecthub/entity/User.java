package com.projecthub.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {
    private Long id;

    private String username;

    private String email;

    private String password;

    private String avatarUrl;

    private String description;
}
