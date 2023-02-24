package com.projecthub.service.impl;

import com.projecthub.dao.ProjectRepository;
import com.projecthub.dto.*;
import com.projecthub.entity.Project;
import com.projecthub.entity.Role;
import com.projecthub.security.JwtService;
import com.projecthub.dao.UserRepository;
import com.projecthub.entity.User;
import com.projecthub.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final PasswordEncoder passwordEncoder;

    private JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserResponseDto getUserById(Long id) {
        User user = userRepository.selectUserById(id).orElseThrow();

        List<Project> projectsWhereOwner = projectRepository.getProjectsByOwnerId(id);
        List<Project> projectsWhereMember = projectRepository.getProjectsByMemberId(id);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setOwnProjects(projectsWhereOwner.stream().map(project -> mapper(project, id)).toList());
        userResponseDto.setJoinedProjects(projectsWhereMember.stream().map(project -> mapper(project, id)).toList());

        return userResponseDto;
    }

    public AuthResponseDto register(RegisterDto registerDto) {
        if (userRepository.existsUserWithEmail(registerDto.getEmail())) {
            throw new RuntimeException("Email is taken");
        }

        User user = User.builder().username(registerDto.getUsername()).email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword())).build();

        userRepository.insertUser(user);

        var jwtToken = jwtService.generateToken(user.getEmail());

        Long id = userRepository.selectUserByEmail(registerDto.getEmail()).orElseThrow().getId();

        return new AuthResponseDto(id, jwtToken);
    }

    public AuthResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        var jwtToken = jwtService.generateToken(loginDto.getEmail());

        Long id = userRepository.selectUserByEmail(loginDto.getEmail()).orElseThrow().getId();

        return new AuthResponseDto(id, jwtToken);
    }

    public void deleteUserById(Long userId) {
        if (!userRepository.existsUserWithId(userId)) {
            throw new RuntimeException("user with id [%s] not found".formatted(userId));
        }
        userRepository.deleteUserWithId(userId);
    }

    public void updateUser(Long userId, User user) {
        User userToUpdate = userRepository.selectUserById(userId).orElseThrow();

        if (user.getUsername() != null && !user.getUsername().equals(userToUpdate.getUsername())) {
            userToUpdate.setUsername(user.getUsername());
        }
        if (user.getEmail() != null && !user.getEmail().equals(userToUpdate.getEmail())) {
            if (userRepository.existsUserWithEmail(user.getEmail())) {
                throw new RuntimeException("Email is already taken");
            }
            userToUpdate.setEmail(user.getEmail());
        }
        if (user.getDescription() != null && !user.getDescription().equals(userToUpdate.getDescription())) {
            userToUpdate.setDescription(user.getDescription());
        }
        if (user.getAvatarUrl() != null && !user.getAvatarUrl().equals(userToUpdate.getAvatarUrl())) {
            userToUpdate.setAvatarUrl(user.getAvatarUrl());
        }

        userRepository.updateUser(userToUpdate);
    }

    private UserProfileProjectDto mapper(Project project, Long userId) {
        UserProfileProjectDto userProjectRoleDto = new UserProfileProjectDto();

        userProjectRoleDto.setProjectId(project.getId());
        userProjectRoleDto.setProjectTitle(project.getTitle());
        userProjectRoleDto.setRoleTitle(project.getRoles().stream().filter(role -> Objects.equals(role.getUser().getId(), userId)).findAny().map(Role::getTitle).orElse(null));
        return userProjectRoleDto;
    }

}
