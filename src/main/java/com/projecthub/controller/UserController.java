package com.projecthub.controller;

import com.projecthub.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    List<User> users;

    @PostConstruct
    public void loadData() {
        User user1 = new User("Mark", "mark@gmail.com");
        User user2 = new User("Bohdan", "bohdan@gmail.com");
        User user3 = new User("Maks", "maks@gmail.com");

        users = new ArrayList<>();

        users.add(user1);
        users.add(user2);
        users.add(user3);

    }

    @GetMapping("/list")
    public String showNames(Model theModel) {
        theModel.addAttribute("users", users);
        return "list-users";
    }
}
