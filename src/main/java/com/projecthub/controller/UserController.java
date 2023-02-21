package com.projecthub.controller;

import com.projecthub.entity.User;
import com.projecthub.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

        users = new ArrayList<>();

        users.add(new User(1L, "Mark", "mark@gmail.com", "pas1"));
        users.add(new User(2L, "Bohdan", "bohdan@gmail.com", "pas2"));
        users.add(new User(3L, "Maks", "maks@gmail.com", "pas3"));
    }

    List<User> users;

    @GetMapping()
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping("{userId}")
    public User getUser(@PathVariable long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.addUser(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }

    @PutMapping("{userId}")
    public void updateUser(@PathVariable Long userId, User user) {
        userService.updateUser(userId,user);
    }
}
