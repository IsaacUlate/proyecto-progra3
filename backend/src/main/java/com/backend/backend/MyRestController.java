package com.backend.backend;
import java.util.Collections;
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
    public List<User> all(int idUsuarioSesion, String token) {
        User user = new User();
    if (databaseService.checkJWT(idUsuarioSesion, token)) {
        return databaseService.getAllUsers();
    } else {
        System.out.println("El token no coincide o el usuario no existe.");
        return Collections.emptyList();
    }
        
        
    }
    //Llama a un usuario existente por ID de la base de datos
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/user/byid")
    public User all(int id, String token, int idUsuarioSesion) {

         if (databaseService.checkJWT(idUsuarioSesion, token)) {
            return databaseService.getUser(id) ;
         }else {
        System.out.println("El token no coincide o el usuario no existe.");
        return null;
    }

        
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
    public void delete(int id, int idUsuarioSesion, String token) {
        if (databaseService.checkJWT(idUsuarioSesion, token)) {
            databaseService.deleteUsuario(id) ;
         }
         
          
    }
    // **Notes**
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/note")

    public void insertNotaEnd(String title, String content, int idUsuarioSesion, String token){

        if (databaseService.checkJWT(idUsuarioSesion, token)) {
            Note note = new Note(0,false,title,content, idUsuarioSesion);
            databaseService.insertNota(note);
         }
        }

    //Notas incompletas
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/note/all")
    public List<Note> allNotes(int idUsuarioSesion, String token) {
        if (databaseService.checkJWT(idUsuarioSesion, token)) {
        return databaseService.getAllNotes(idUsuarioSesion) ;
    } else {
       return Collections.emptyList();
    }
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/note/byid")
    public void update(String content, String title, int noteID, int idUsuarioSesion, String token) {
        if (databaseService.checkJWT(idUsuarioSesion, token)) {
        Note note = new Note(noteID, false, title, content, idUsuarioSesion);
    databaseService.updateNota(note);
    }
    

    
}

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/note/complete")
    public void updateNotaCompletada(int id, int idUsuarioSesion, String token) {
        if (databaseService.checkJWT(idUsuarioSesion, token)) {
        Note note = new Note(id, true, null, null, 0);
        databaseService.updateNotaCompletada(note) ;
    }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/note/complete")
    public List<Note> allCompleteNotes(int idUsuarioSesion, String token) {
        if (databaseService.checkJWT(idUsuarioSesion, token)) {
        return databaseService.getAllCompleteNotes(idUsuarioSesion) ;
    } else {
       return Collections.emptyList();
    }
        
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/notes")
    public List<Note> allNotesAllUsers(int idUsuarioSesion, String token) {
        if (databaseService.checkJWT(idUsuarioSesion, token)) {
        return databaseService.getAllNotesAllUsers() ;
    } else {
       return Collections.emptyList();
    }
        
    }
        
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/note")
    public void deleteNota(int id, int idUsuarioSesion, String token) {
        if (databaseService.checkJWT(idUsuarioSesion, token)) {
            databaseService.deleteNota(id) ;
         }
        
    }
}