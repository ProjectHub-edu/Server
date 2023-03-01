package com.projecthub.service.impl;

import com.projecthub.dao.ProjectRepository;
import com.projecthub.dao.UserRepository;
import com.projecthub.dto.*;
import com.projecthub.entity.Project;
import com.projecthub.entity.Role;
import com.projecthub.entity.User;
import com.projecthub.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public List<ProjectDto> getAllProjects(){
        return projectRepository.selectAllProjects().stream().map(this::mapProjectToProjectDto).collect(Collectors.toList());
    }
    public ProjectProfileDto getProjectById(Long id){
        return mapProjectToProjectProfileDto(projectRepository.selectProjectById(id).orElseThrow());
    }
    public void addProject(ProjectAddDto projectAddDto){
        User user = userRepository.selectUserById(projectAddDto.getOwnerId()).orElseThrow();

        Role role = Role.builder().title(projectAddDto.getOwnerRoleTitle())
                .description(projectAddDto.getOwnerRoleDescription())
                .user(user).build();

        Project project = Project.builder().title(projectAddDto.getTitle())
                .description(projectAddDto.getDescription())
                .owner(role).build();

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

    private ProjectDto mapProjectToProjectDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setTitle(project.getTitle());
        projectDto.setDescription(project.getDescription());
        projectDto.setStatus(project.getStatus());
        projectDto.setImageUrl(project.getProjectImgUrl());
        return projectDto;
    }

    private ProjectProfileDto mapProjectToProjectProfileDto(Project project) {
        ProjectProfileDto projectProfileDto = new ProjectProfileDto();

        List<RoleDto> roleDtos = project.getRoles() == null ? new ArrayList<>() : project.getRoles().stream().map(this::mapRoleToDto).collect(Collectors.toList());
        roleDtos.add(mapRoleToDto(project.getOwner()));

        projectProfileDto.setId(project.getId());
        projectProfileDto.setTitle(project.getTitle());
        projectProfileDto.setDescription(project.getDescription());
        projectProfileDto.setStatus(project.getStatus());
        projectProfileDto.setProjectImgUrl(project.getProjectImgUrl());
        projectProfileDto.setRoles(roleDtos);
        return projectProfileDto;
    }

    private RoleDto mapRoleToDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(role.getTitle());
        roleDto.setDescription(role.getDescription());
        roleDto.setUser(mapUserToDto(role.getUser()));

        return roleDto;
    }

    private ProjectUserDto mapUserToDto(User user) {
        ProjectUserDto projectUserDto = new ProjectUserDto();
        projectUserDto.setId(user.getId());
        projectUserDto.setUsername(user.getUsername());
        projectUserDto.setAvatarUrl(user.getAvatarUrl());
        return projectUserDto;
    }
}
