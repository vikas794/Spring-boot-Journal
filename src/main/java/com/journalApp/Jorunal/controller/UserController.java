package com.journalApp.Jorunal.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.journalApp.Jorunal.entity.User;
import com.journalApp.Jorunal.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/UserController")
public class UserController {

    @Autowired UserService UserService;

    @GetMapping
    public List<User> getAllUser() {
        return UserService.getAll();
    }

    @PostMapping
    public User createUser(@RequestBody User userEntry) {
        UserService.saveEntry(userEntry);
        return userEntry;
    }

    @GetMapping("id/{myid}")
    public Optional<User> getUserById(@PathVariable ObjectId myid) {
        return UserService.findById(myid);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updatUser(@RequestBody User user, @PathVariable String username) {
        User userInDB = UserService.findByUserName(username);
        if (userInDB != null) {
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            UserService.saveEntry(userInDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}