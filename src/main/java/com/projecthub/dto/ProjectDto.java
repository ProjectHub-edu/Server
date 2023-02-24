package com.projecthub.dto;

import com.projecthub.entity.Role;
import com.projecthub.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private long id;

    private String title;

    private String description;

    private Status status;

    private String ImageUrl;


}
