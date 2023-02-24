package com.projecthub.service;

import com.projecthub.dto.ProjectAddDto;
import com.projecthub.dto.ProjectDto;
import com.projecthub.dto.ProjectProfileDto;
import com.projecthub.entity.Project;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> getAllProjects();

    ProjectProfileDto getProjectById(Long id);

    void addProject(ProjectAddDto project);

    void deleteProjectById(Long id);

    void updateProject(Long projectId, Project project);
}
