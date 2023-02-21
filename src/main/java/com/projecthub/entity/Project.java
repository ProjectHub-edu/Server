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

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public String getProjectImgUrl() {
        return projectImgUrl;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public List<User> getOwners() {
        return owners;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setProjectImgUrl(String projectImgUrl) {
        this.projectImgUrl = projectImgUrl;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }
}
