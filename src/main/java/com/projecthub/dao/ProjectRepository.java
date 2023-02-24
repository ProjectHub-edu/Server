package com.projecthub.dao;

import com.projecthub.entity.Project;
import com.projecthub.entity.Role;
import com.projecthub.entity.User;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    List<Project> selectAllProjects();
    Optional<Project> selectProjectById(Long id);
    void insertProject(Project project);
    boolean existsProjectWithId(Long id);
    void deleteProjectById(Long id);
    void updateProject(Project project);

    List<Project> getProjectsByOwnerId(Long id);

    List<Project> getProjectsByMemberId(Long id);

}
