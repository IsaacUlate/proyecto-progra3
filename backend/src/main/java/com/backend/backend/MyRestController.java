package com.backend.backend;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.WebRequest;


@RestController
@RequestMapping("/api")
public class MyRestController {
    @Autowired
    private DatabaseService databaseService;

    //login
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/user/login")
    public User authenticateUser(String username, String password) {
        User  tmpUser =  databaseService.authenticateUser(username,password);
        if (tmpUser != null) {
            tmpUser.setJTW();
        }
        
        return tmpUser;
        //return databaseService.authenticateUser(username, password);
    }

    //Llama a todos los usuarios de la base de datos
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/user/all")
    public List<User> all() {
        return databaseService.getAllUsers() ;
    }
    //Llama a un usuario existente por ID de la base de datos
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/user/byid")
    public User all(int id) {
        return databaseService.getUser(id) ;
    }
    //Inserta Usuarios a la base de datos
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/user")
    public void insert(@RequestBody User user){

        databaseService.insertUsuario(user);
    }
    //Borra usuarios de la base de datos
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/user")
    public void delete(int id) {
        databaseService.deleteUsuario(id) ;
    }
    // **Notes**
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/note")

    public void insertNotaEnd(String title, String content, int userID){

        Note note = new Note(0,false,content, title, userID);
        
        databaseService.insertNota(note);
    }
    //Notas incompletas
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/note/all")
    public List<Note> allNotes(int idUser) {
        return databaseService.getAllNotes(idUser) ;
    }

    @CrossOrigin(origins = "http://localhost:3000")
@PutMapping("/note/byid")
public void update(String content) {

    System.out.println("content: " + content);
   // System.out.println("noteID: " + noteID);

    //Note note = new Note(noteID, false, title, content, userID);
   // databaseService.updateNota("note");
}


    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/note/complete")
    public void updateNotaCompletada(int id) {

        Note note = new Note(id, true, null, null, 0);
        databaseService.updateNotaCompletada(note) ;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/note/complete")
    public List<Note> allCompleteNotes(int id) {
        return databaseService.getAllCompleteNotes(id) ;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/notes")
    public List<Note> allNotes() {
        return databaseService.getAllNotesAllUsers() ;
    }
    
    /* @CrossOrigin(origins = "http://localhost:3000")
     @PostMapping("/login")
    public User loginUser(String username, String password) {

        User  tmpUser =  databaseService.authenticateUser(username,password) ;
        tmpUser.setJTW();
        
        return tmpUser;
        }*/
    //         @CrossOrigin(origins = "http://localhost:3000")
    // @PostMapping("/login")
    // public ResponseEntity<String> loginUser(String username, String password) {
    //     User user = databaseService.authenticateUser(username, password);
    //     if (user != null) {
    //         return new ResponseEntity<String>("Login successful", HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<String>("Invalid username or password", HttpStatus.UNAUTHORIZED);
    //     }
    // }

        
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/note")
    public void deleteNota(int id) {

        databaseService.deleteNota(id) ;
    }
}