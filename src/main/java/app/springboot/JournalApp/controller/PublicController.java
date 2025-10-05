package app.springboot.JournalApp.controller;

import app.springboot.JournalApp.entity.Users;
import app.springboot.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;


    @GetMapping("/health-check")
    public String healthCheck(){
        return "Working Correctly !";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody Users user) {
        userService.saveEntry((user));
    }
}
