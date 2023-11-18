package com.backend.backend;

import java.util.List;

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

    //Prueba
    @GetMapping
    public String hello() {
        return "Hello";
    }

    //Llama a todos los usuarios de la base de datos
    @GetMapping("/user/all")
    public List<User> all() {
        return databaseService.getAllUsers() ;
    }

    //Llama a un usuario existente por ID de la base de datos
    @GetMapping("/user/byid")
    public User all(int id) {
        return databaseService.getUser(id) ;
    }

    //Inserta Usuarios a la base de datos
    @PostMapping("/user")
    public void insert(String name, String lastnames, String email, String username, String password){

        User user = new User(0, name, lastnames, email, username, password);
        databaseService.insertUsuario(user);
    }
    
    //Borra usuarios de la base de datos
    @DeleteMapping("/user")
    public void delete(int id) {
 
        databaseService.deleteUsuario(id) ;
    }

    // **Notes**

    @PostMapping("/note")
    public void insert(String title, String content, int userID){

        Note note = new Note(0,false,content, title, userID);
        databaseService.insertNota(note);
    }

    @GetMapping("/note/all")
    public List<Note> allNotes() {
        return databaseService.getAllNotes() ;
    }


    
}


