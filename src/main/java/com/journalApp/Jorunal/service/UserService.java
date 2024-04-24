package com.journalApp.Jorunal.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.journalApp.Jorunal.entity.User;
import com.journalApp.Jorunal.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User User){
        try {
            userRepository.save(User);
        } catch (Exception e) {
            log.error("Exception ", e);
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id); 
    }

    public void DeleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void deleteByUsername(String Username) {
        userRepository.deleteByUserName(Username);
    }
}
