package com.journalApp.Jorunal.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.journalApp.Jorunal.entity.JournalEntry;

public interface JournalEntryRepository extends MongoRepository <JournalEntry, ObjectId>{
    
}
