package app.springboot.JournalApp.service;

import app.springboot.JournalApp.entity.JournalEntry;
import app.springboot.JournalApp.entity.Users;
import app.springboot.JournalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Slf4j
@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository ;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
            try {
                Users user = userService.findByUserName((userName));
                journalEntry.setDate(LocalDateTime.now());
                JournalEntry saved = journalEntryRepository.save(journalEntry);
                user.setUserName(null);
                user.getJournalEntries().add(saved);
                userService.saveEntry(user);
            } catch (Exception e) {
                System.out.println(e);
                throw new RuntimeException("An Error Occurred while Saving the Entry");
            }
    }

    public void saveEntry(JournalEntry journalEntry){
        try {
            journalEntryRepository.save(journalEntry);
        } catch (Exception e) {
            log.error("Exception ", e);
        }
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id) ;
    }

    public void deleteById(ObjectId id, String userName){
        Users user = userService.findByUserName((userName));
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}

// Controller ---> Service ---> Repository