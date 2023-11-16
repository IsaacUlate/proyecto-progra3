package com.backend.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyRestController {
    @Autowired
    DatabaseService databaseService = new DatabaseService();
    
    @GetMapping
    public String hello() {
        return "Hello";
    }

    @PostMapping("/all")
    public void insert(String name, String lastnames, String email, String username, String password){

        User user = new User(0, name, lastnames, email, username, password);
        databaseService.insertUsuario(user);
    }

}
