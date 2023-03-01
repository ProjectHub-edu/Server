package com.projecthub.controller;

import com.projecthub.dto.ProjectAddDto;
import com.projecthub.dto.ProjectDto;
import com.projecthub.dto.ProjectProfileDto;
import com.projecthub.entity.Project;
import com.projecthub.service.impl.ProjectServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {

    private final ProjectServiceImpl projectService;

    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @GetMapping()
    public List<ProjectDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("{projectId}")
    public ProjectProfileDto getProject(@PathVariable Long projectId) {
        return projectService.getProjectById(projectId);
    }

    @PostMapping("/new")
    public ResponseEntity addProject(@RequestBody ProjectAddDto projectAddDto) {
        projectService.addProject(projectAddDto);
        return new ResponseEntity("Project added successfully", HttpStatus.OK);
    }

    @DeleteMapping("{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProjectById(projectId);
    }

    @PutMapping("{projectId}")
    public ResponseEntity updateProject(@PathVariable Long projectId, Project project) {
        projectService.updateProject(projectId,project);
        return new ResponseEntity("Project updated successfully", HttpStatus.OK);

    }

}
