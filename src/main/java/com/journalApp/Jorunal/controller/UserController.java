package com.journalApp.Jorunal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journalApp.Jorunal.entity.User;
import com.journalApp.Jorunal.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    // will add to the admin if reqiured
    // @GetMapping
    // public List<User> getAllUser() {
    // return userService.getAll();
    // }

    @GetMapping("{username}")
    public ResponseEntity<?> getUserByUserName(@PathVariable String username) {
        return new ResponseEntity<>(userService.findByUserName(username), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updatUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDB = userService.findByUserName(userName);
        userInDB.setUserName(user.getUserName());
        userInDB.setPassword(user.getPassword());
        userService.saveEntry(userInDB);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity<?> deleteUserByUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userService.deleteByUsername(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}