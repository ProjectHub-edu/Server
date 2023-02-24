package com.projecthub.service;

import com.projecthub.dto.AuthResponseDto;
import com.projecthub.dto.LoginDto;
import com.projecthub.dto.RegisterDto;
import com.projecthub.dto.UserResponseDto;
import com.projecthub.entity.User;

public interface UserService{
    UserResponseDto getUserById(Long id);

    AuthResponseDto register(RegisterDto registerDto);

    AuthResponseDto login(LoginDto loginDto);

    void deleteUserById(Long userId);

    void updateUser(Long userId, User user);

}
