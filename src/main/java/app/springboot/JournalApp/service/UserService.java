package app.springboot.JournalApp.service;

import app.springboot.JournalApp.entity.JournalEntry;
import app.springboot.JournalApp.entity.Users;
import app.springboot.JournalApp.repository.JournalEntryRepository;
import app.springboot.JournalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Slf4j
@Component
public class UserService {

    @Autowired
    private UserRepository userRepository ;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(Users users){
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRoles(Arrays.asList("USER"));
        userRepository.save(users);
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