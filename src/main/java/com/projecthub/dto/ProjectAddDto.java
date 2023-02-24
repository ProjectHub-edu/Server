package com.projecthub.dto;

import com.projecthub.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAddDto {
    private String title;

    private String description;

    private Long ownerId;

    private String ownerRoleTitle;

    private String ownerRoleDescription;
}
