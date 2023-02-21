package com.projecthub.entity;

import java.util.List;

public class Role {
    private Long id;

    private String description;

    private Project project;

    private User user;

    public Role() {}

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Project getProject() {
        return project;
    }

    public User getUser() {
        return user;
    }

}
