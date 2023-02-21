package com.projecthub.entity;

import java.util.List;

public class User {
    private Long id;

    private String userName;

    private String email;

    private String password;

    private String avatarUrl;

    private String description;

    private int honor;

    private List<User> friends;

    private List<Project> projects;

    private List<String> technologies;


    public User() {}

    public User(Long id, String userName, String email, String password)  {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getDescription() {
        return description;
    }

    public int getHonor() {
        return honor;
    }

    public List<User> getFriends() {
        return friends;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHonor(int honor) {
        this.honor = honor;
    }
}
