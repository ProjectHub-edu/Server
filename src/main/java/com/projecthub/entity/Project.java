package com.projecthub.entity;

import java.util.List;

public class Project {
    private long id;
    private long owner;
    private List<Role> roles;

    public Project() {

    }
    public Project(long id, long owner) {
        this.id = id;
        this.owner = owner;
    }
}
