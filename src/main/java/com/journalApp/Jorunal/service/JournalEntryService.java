package com.journalApp.Jorunal.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.journalApp.Jorunal.entity.JournalEntry;
import com.journalApp.Jorunal.entity.User;
import com.journalApp.Jorunal.repository.JournalEntryRepository;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository JournalEntryRepository;

    @Autowired
    private UserService UserService;

    public void saveEntry(JournalEntry JournalEntry, String username) {
        User user = UserService.findByUserName(username);
        JournalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = JournalEntryRepository.save(JournalEntry);
        user.getJournalEntries().add(saved);
        UserService.saveEntry(user);
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
