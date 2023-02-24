package com.projecthub.entity;

import com.projecthub.enumeration.Status;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class Project {
    private long id;

    private String title;

    private String description;

    private Status status;

    private String projectImgUrl;

    private Date startDate;

    private Date deadLine;

    private Role owner;

    private List<Role> roles;

    private List<String> technologies;
}
