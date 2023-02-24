package com.projecthub.dao;

import com.projecthub.entity.Project;
import com.projecthub.entity.Role;
import com.projecthub.entity.User;
import com.projecthub.enumeration.Status;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository("ProjectList")
public class ProjectListRepository implements ProjectRepository {

    private static final List<Project> projects;

    static {
        List<User> users = UserListRepository.users;

        projects = new ArrayList<>();

        List<Role> roles = new ArrayList<>();
        roles.add(Role.builder().id(1L).title("frontend dev").title("React").user(users.get(0)).build());
        roles.add(Role.builder().id(2L).title("backend dev").title("Spring").user(users.get(1)).build());
        roles.add(Role.builder().id(3L).title("android dev").title("Kotlin").user(users.get(2)).build());

        Role owner1 = Role.builder().id(1L).title("Team lead").title("Manage teams").user(users.get(3)).build();
        Role owner2 = Role.builder().id(2L).title("Solution architect").title("Application design").user(users.get(0)).build();

        projects.add(Project.builder().id(1L).title("project1").description("project1").status(Status.IN_PROGRESS)
                .startDate(new Date()).deadLine(new Date())
                .owner(owner1).roles(roles).build());
        projects.add(Project.builder().id(2L).title("project2").description("project2").status(Status.IN_PROGRESS)
                .startDate(new Date()).deadLine(new Date())
                .owner(owner2).roles(roles).build());
    }

    @Override
    public List<Project> selectAllProjects() {
        return projects;
    }

    @Override
    public Optional<Project> selectProjectById(Long id) {
        return projects.stream().filter(project -> Objects.equals(project.getId(), id)).findAny();
    }

    public List<Project> getProjectsByOwnerId(Long id){
        return selectAllProjects().stream().filter(project -> Objects.equals(project.getOwner().getUser().getId(), id)).collect(Collectors.toList());
    }

    @Override
    public List<Project> getProjectsByMemberId(Long id) {
        return selectAllProjects().stream().filter(project -> project.getRoles().stream().map(role -> role.getUser().getId()).anyMatch(userId -> Objects.equals(userId, id))).collect(Collectors.toList());
    }

    @Override
    public void insertProject(Project project) {
        projects.add(project);
    }

    @Override
    public boolean existsProjectWithId(Long id) {
        return projects.stream().anyMatch(project -> Objects.equals(project.getId(), id));
    }

    @Override
    public void deleteProjectById(Long id) {
        projects.remove(selectProjectById(id).orElseThrow());
    }

    @Override
    public void updateProject(Project project) {
        projects.set(projects.indexOf(selectProjectById(project.getId()).orElseThrow()),project);
    }
}
