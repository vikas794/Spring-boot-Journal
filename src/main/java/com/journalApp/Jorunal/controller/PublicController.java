package com.journalApp.Jorunal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journalApp.Jorunal.entity.User;
import com.journalApp.Jorunal.service.UserService;

@RestController
@RequestMapping("/public")
public class HealthCheck {

    @Autowired
    UserService userService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "ok";
    }

    @PostMapping("create-user")
    public User createUser(@RequestBody User userEntry) {
        userService.saveEntry(userEntry);
        return userEntry;
    }
}
