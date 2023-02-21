package com.projecthub.dao;

import com.projecthub.entity.Project;
import com.projecthub.entity.User;
import com.projecthub.enumeration.Status;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("List")
public class ProjectListRepository implements ProjectRepository {

    private static final List<Project> projects;

    static {
        projects = new ArrayList<>();

        projects.add(new Project(1L,"project1", "project1", Status.IN_PROGRESS, "", new Date(),new Date()));
        projects.add(new Project(2L,"project2", "project2", Status.NOT_STARTED, "", new Date(),new Date()));
        projects.add(new Project(3L,"project3", "project3", Status.ENDED, "", new Date(),new Date()));
    }

    @Override
    public List<Project> selectAllProjects() {
        return projects;
    }

    @Override
    public Optional<Project> selectProjectById(Long id) {
        return projects.stream().filter(project -> Objects.equals(project.getId(), id)).findAny();
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
