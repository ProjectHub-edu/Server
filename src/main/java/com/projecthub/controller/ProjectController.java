package com.projecthub.controller;

import com.projecthub.entity.Project;
import com.projecthub.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping()
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("{projectId}")
    public Project getProject(@PathVariable long projectId) {
        return projectService.getProjectById(projectId);
    }

    @DeleteMapping("{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProjectById(projectId);
    }

    @PutMapping("{projectId}")
    public void updateProject(@PathVariable Long projectId, Project project) {
        projectService.updateProject(projectId,project);
    }
}
