package com.projecthub.entity;

import com.projecthub.enumeration.Status;

import java.util.Date;
import java.util.List;

public class Project {
    private long id;

    private String title;

    private String description;

    private Status status;

    private String projectImgUrl;

    private Date startDate;

    private Date deadLine;

    private List<User> owners;

    private List<Role> roles;

    private List<String> technologies;

    public Project() {

    }

    public Project(long id, String title, String description, Status status, String projectImgUrl, Date startDate, Date deadLine) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.projectImgUrl = projectImgUrl;
        this.startDate = startDate;
        this.deadLine = deadLine;
    }


}
