package com.backend.backend;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //se inyecta el servicio de base de datos
    @Autowired
    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    //Se crea getAllUsers para mostrar todos los usuarios
    public List<User> getAllUsers() {
        try {
            String query = "SELECT * FROM Usuario";
            List<Map<String, Object>> resultDB = jdbcTemplate.queryForList(query);
            List<User> GetUsers = new ArrayList<>();

            for (Map<String, Object> row : resultDB) {
                int UserID = (int) row.get("ID_Usuario");
                String Name = (String) row.get("Nombre");
                String Lastnames = (String) row.get("Apellidos");
                String Email = (String) row.get("Email");
                String Username = (String) row.get("Nombre_Usuario");
                String Password = (String) row.get("Contraseña");

                User Usuario = new User(UserID, Name, Lastnames, Email, Username, Password);
                GetUsers.add(Usuario);
            }
            return GetUsers;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //Se crea getUser para mostrar a los usuarios por ID
    public User getUser(int id) {
        System.out.println("logId = " + id);
        try {
            String query = "SELECT * FROM USUARIO WHERE ID_Usuario = ?";

            return jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
                int UserID = (int)rs.getInt("ID_Usuario");
                String Name = rs.getString("Nombre");
                String Lastnames = rs.getString("Apellidos");
                String Email = rs.getString("Email");
                String Username = rs.getString("Nombre_Usuario");
                String Password = rs.getString("Contraseña");
              
                return new User(UserID, Name, Lastnames, Email, Username, Password);
            }, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Se crea el insertUsuario para agregar usuarios
    public void insertUsuario(User user) {
        try {
            String query = "INSERT USUARIO SET Nombre = ?, Apellidos = ?, Email = ?, Nombre_Usuario = ?, Contraseña = ? ";
            jdbcTemplate.update(query, user.getName(),user.getLastnames(),user.getEmail(), user.getUsername(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Se crea el deleteUsuario para eliminar usuarios
    public int deleteUsuario(int id) {
        try {
            String query = "DELETE FROM USUARIO WHERE ID_Usuario = ?"; 
            jdbcTemplate.update(query, id);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //******** Notes ********
    public void insertNota(Note note) {
        try {
            String query = "INSERT NOTAS SET ESTADO = ?,TITULO = ?, CONTENIDO = ?, ID_USUARIO = ? "; 
            jdbcTemplate.update(query, note.getStatus(),note.getTitle(),note.getContent(),note.getUserID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Notas incompletas
    public List<Note> getAllNotes(int id) {
        try {
            String query = "SELECT * FROM Notas WHERE ESTADO = 0 AND ID_USUARIO =?;";
            List<Map<String, Object>> resultDB = jdbcTemplate.queryForList(query, id);
            List<Note> GetNotes = new ArrayList<>();

            for (Map<String, Object> row : resultDB) {

                int noteID = (int) row.get("ID_NOTAS");
                Boolean status = (boolean) row.get("ESTADO");
                String title = (String) row.get("TITULO");
                String content = (String) row.get("CONTENIDO");
                int userID = (int) row.get("ID_USUARIO");
               
                Note note = new Note(noteID, status, title, content, userID);
                GetNotes.add(note);
            }
            return GetNotes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //Notas completadas
    public List<Note> getAllCompleteNotes(int id) {
        try {
            String query = "SELECT * FROM Notas WHERE ESTADO = 1 AND ID_USUARIO =?;";
            List<Map<String, Object>> resultDB = jdbcTemplate.queryForList(query, id);
            List<Note> GetNotes = new ArrayList<>();

            for (Map<String, Object> row : resultDB) {

                int noteID = (int) row.get("ID_NOTAS");
                Boolean status = (boolean) row.get("ESTADO");
                String title = (String) row.get("TITULO");
                String content = (String) row.get("CONTENIDO");
                int userID = (int) row.get("ID_USUARIO");
               
                Note note = new Note(noteID, status, title, content, userID);
                GetNotes.add(note);
            }
            return GetNotes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateNota(Note note) {
        try {
            String query = "UPDATE NOTAS SET TITULO = ?, CONTENIDO = ? WHERE ID_NOTAS = ?";
            jdbcTemplate.update(query, note.getTitle(),note.getContent(), note.getNoteID());
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions if needed
        }
    }

    public void updateNotaCompletada(Note note) {
        try {
            String query = "UPDATE NOTAS SET ESTADO = ? WHERE ID_NOTAS = ?";
            jdbcTemplate.update(query, note.getStatus(), note.getNoteID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int deleteNota(int id) {
        try {
            String query = "DELETE FROM NOTAS WHERE ID_NOTAS = ?";
            jdbcTemplate.update(query, id);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
  


    public User authenticateUser(String username, String password) {
        System.out.println("logId = " + username);
        try {
            String query = "SELECT * FROM USUARIO WHERE Username = ? and Password =?";

            return jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
                int UserID = (int)rs.getInt("ID_Usuario");
                String Name = rs.getString("Nombre");
                String Lastnames = rs.getString("Apellidos");
                String Email = rs.getString("Email");
                String Username = rs.getString("Nombre_Usuario");
                String Password = rs.getString("Contraseña");
        
                return new User(UserID,Name,Lastnames,Email,Username,Password);
            }, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
  
    public List<Note> llamarNotas(List<Note> note){
        try{
       List<Note> GetNotes = new ArrayList<>();
       String query = "SELECT * FROM Notas";
        
       return GetNotes;
       }
      
       catch (Exception e) {
            e.printStackTrace();
            return null;
        }
 }

}
