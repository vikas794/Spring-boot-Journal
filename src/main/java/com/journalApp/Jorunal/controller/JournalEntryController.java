package com.journalApp.Jorunal.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.journalApp.Jorunal.entity.JournalEntry;
import com.journalApp.Jorunal.entity.User;
import com.journalApp.Jorunal.service.JournalEntryService;
import com.journalApp.Jorunal.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService JournalEntryService;

    @Autowired 
    private UserService userService; 

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAll(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.ALREADY_REPORTED);
        }else if (all == null || all.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myid) {
        Optional<JournalEntry> journalEntry = JournalEntryService.findById(myid);
        if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }else if (!journalEntry.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username) {
        // return new ResponseEntity<>(myEntry, HttpStatus.OK);
        try {
            JournalEntryService.saveEntry(myEntry, username);
            return new ResponseEntity<>(myEntry, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("id/{username}/{myid}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myid, @PathVariable String username) {
        JournalEntryService.DeleteById(myid, username);
        return new ResponseEntity<>("Data Deleted", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{myid}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry) {
        JournalEntry old = JournalEntryService.findById(myid).orElse(null);
        // if (old != null) {
        //     old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
        //     old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
        //     JournalEntryService.saveEntry(old);
        //     return new ResponseEntity<>(HttpStatus.OK);
        // }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}