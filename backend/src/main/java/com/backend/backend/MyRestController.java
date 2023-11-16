package com.backend.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyRestController {
    @Autowired
    private DatabaseService databaseService;
    
    @GetMapping
    public String hello() {
        return "Hello";
    }

    @PostMapping("/all")
    public void insert(String name, String lastnames, String email, String username, String password){

        User user = new User(0, name, lastnames, email, username, password);
        databaseService.insertUsuario(user);
    }

    @DeleteMapping("/byid")
    public void delete(int id) {
 
        databaseService.deleteUsuario(id) ;
    }

    
}


