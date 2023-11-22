package com.backend.backend;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyRestController {
    @Autowired
    private DatabaseService databaseService;

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
    //Notas incompletas
    @GetMapping("/note/all")
    public List<Note> allNotes() {
        return databaseService.getAllNotes() ;
    }

    @PutMapping("/note/byid")
    public void update(int id, String title, String content) {

        Note note = new Note(id, false, title, content, 0);
        databaseService.updateNota(note) ;
    }

    @PutMapping("/note/complete")
    public void updateNotaCompletada(int id) {

        Note note = new Note(id, true, null, null, 0);
        databaseService.updateNotaCompletada(note) ;
    }

    @GetMapping("/note/complete")
    public List<Note> allCompleteNotes() {
        return databaseService.getAllCompleteNotes() ;
    }

    @DeleteMapping("/note")
    public void deleteNota(int id) {

        databaseService.deleteNota(id) ;
    }
}