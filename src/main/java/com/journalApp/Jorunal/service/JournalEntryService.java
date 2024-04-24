package com.journalApp.Jorunal.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.journalApp.Jorunal.entity.JournalEntry;
import com.journalApp.Jorunal.entity.User;
import com.journalApp.Jorunal.repository.JournalEntryRepository;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository JournalEntryRepository;

    @Autowired
    private UserService UserService;

    @Transactional
    public void saveEntry(JournalEntry JournalEntry, String username) {
        try {
            User user = UserService.findByUserName(username);
            JournalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = JournalEntryRepository.save(JournalEntry);
            user.getJournalEntries().add(saved);
            UserService.saveEntry(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("AN error had occur while saving an entry", e);
        }
    }

    public void saveEntry(JournalEntry JournalEntry) {
        JournalEntryRepository.save(JournalEntry);
    }

    public List<JournalEntry> getAll() {
        return JournalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return JournalEntryRepository.findById(id);
    }

    public void DeleteById(ObjectId id, String username) {
        User user = UserService.findByUserName(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        UserService.saveEntry(user);
        JournalEntryRepository.deleteById(id);
    }
}
    