package com.journalApp.Jorunal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.journalApp.Jorunal.entity.User;
import com.journalApp.Jorunal.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/UserController")
public class UserController {

    @Autowired UserService userService;

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAll();
    }

    @PostMapping
    public User createUser(@RequestBody User userEntry) {

        userService.saveEntry(userEntry);
        return userEntry;
    }

    @GetMapping("{username}")
    public ResponseEntity<?> getUserByUserName(@PathVariable String username) {
        return new ResponseEntity<>(userService.findByUserName(username),HttpStatus.BAD_GATEWAY);
    }


    @PutMapping("{username}")
    public ResponseEntity<?> updatUser(@RequestBody User user, @PathVariable String username) {
        User userInDB = userService.findByUserName(username);
        if (userInDB != null) {
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userService.saveEntry(userInDB);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}