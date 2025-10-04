package app.springboot.JournalApp.service;

import app.springboot.JournalApp.entity.JournalEntry;
import app.springboot.JournalApp.entity.Users;
import app.springboot.JournalApp.repository.JournalEntryRepository;
import app.springboot.JournalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Slf4j
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository ;

    public void saveEntry(Users users){
        try {
//            users.setDate(LocalDateTime.now());
            userRepository.save(users);
        } catch (Exception e) {
            log.error("Exception ", e);
        }
    }

    public List<Users> getAll(){
        return userRepository.findAll();
    }

    public Optional<Users> findById(ObjectId id){
        return userRepository.findById(id) ;
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public Users findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}

// Controller ---> Service ---> Repository