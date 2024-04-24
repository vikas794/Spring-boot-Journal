package com.journalApp.Jorunal.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.journalApp.Jorunal.entity.User;

public interface UserRepository extends MongoRepository <User, ObjectId>{
    User findByUserName(String username);
    User deleteByUserName(String username);
}
