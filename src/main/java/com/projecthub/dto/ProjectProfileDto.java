package com.projecthub.dto;

import com.projecthub.entity.Role;
import com.projecthub.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectProfileDto {
    private long id;

    private String title;

    private String description;

    private Status status;

    private String projectImgUrl;

    private Date created_date;

    private List<RoleDto> roles;
}
