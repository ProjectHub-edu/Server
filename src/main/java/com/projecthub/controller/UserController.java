package com.projecthub.controller;

import com.projecthub.dto.AuthResponseDto;
import com.projecthub.dto.LoginDto;
import com.projecthub.dto.RegisterDto;
import com.projecthub.dto.UserResponseDto;
import com.projecthub.entity.User;
import com.projecthub.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("{userId}")
    public UserResponseDto getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @PostMapping("/new")
    public ResponseEntity<AuthResponseDto> registerUser(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(userService.register(registerDto));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity("User deleted successfully", HttpStatus.OK);

    }

    @PutMapping("{userId}")
    public ResponseEntity updateUser(@PathVariable Long userId, User user) {
        userService.updateUser(userId,user);
        return new ResponseEntity("User updated successfully", HttpStatus.OK);
    }
}
