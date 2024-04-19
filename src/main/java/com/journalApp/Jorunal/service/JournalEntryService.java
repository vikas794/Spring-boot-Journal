package com.journalApp.Jorunal.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.journalApp.Jorunal.entity.JournalEntry;
import com.journalApp.Jorunal.repository.JournalEntryRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JournalEntryService {
    
    @Autowired
    private JournalEntryRepository JournalEntryRepository;

    public void saveEntry(JournalEntry JournalEntry){
        try {
            JournalEntry.setData(LocalDateTime.now());
            JournalEntryRepository.save(JournalEntry);
        } catch (Exception e) {
            log.error("Exception ", e);
        }

    }

    public List<JournalEntry> getAll() {
        return JournalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return JournalEntryRepository.findById(id); 
    }

    public void DeleteById(ObjectId id) {
        JournalEntryRepository.deleteById(id);
    }
}
