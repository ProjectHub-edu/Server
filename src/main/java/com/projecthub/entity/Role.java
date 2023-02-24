package com.projecthub.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role {
    private Long id;

    private String title;

    private String description;

    private User user;
}
