package com.projecthub.service;

import com.projecthub.dao.ProjectRepository;
import com.projecthub.dao.UserRepository;
import com.projecthub.entity.Project;
import com.projecthub.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects(){
        return projectRepository.selectAllProjects();
    }
    public Project getProjectById(Long id){
        return projectRepository.selectProjectById(id).orElseThrow();
    }
    public void addProject(Project project){
        projectRepository.insertProject(project);
    }

    public void deleteProjectById(Long id){
        if(!projectRepository.existsProjectWithId(id)){
            throw new RuntimeException("project with id [%s] not found".formatted(id));
        }
        projectRepository.deleteProjectById(id);
    }
    public void updateProject(Long projectId,Project project){
        Project projectToUpdate = projectRepository.selectProjectById(projectId).orElseThrow();

        if(project.getTitle() != null && !project.getTitle().equals(projectToUpdate.getTitle())){
            projectToUpdate.setTitle(project.getTitle());
        }
        if(project.getDescription() != null && !project.getDescription().equals(projectToUpdate.getDescription())){
            projectToUpdate.setDescription(project.getDescription());
        }
        if(project.getProjectImgUrl() != null && !project.getProjectImgUrl().equals(projectToUpdate.getProjectImgUrl())){
            projectToUpdate.setProjectImgUrl(project.getProjectImgUrl());
        }
        if(project.getStartDate() != null && !project.getStartDate().equals(projectToUpdate.getStartDate())){
            projectToUpdate.setStartDate(project.getStartDate());
        }
        if(project.getDeadLine() != null && !project.getDeadLine().equals(projectToUpdate.getDeadLine())){
            projectToUpdate.setDeadLine(project.getDeadLine());
        }
        if(project.getStatus() != null && !project.getStatus().equals(projectToUpdate.getStatus())){
            projectToUpdate.setStatus(project.getStatus());
        }

        projectRepository.updateProject(projectToUpdate);
    }
}
