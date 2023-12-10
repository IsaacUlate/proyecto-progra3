package com.backend.backend;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
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
    
    private String authenticatedUserToken;
    //User user = new User();
    //String token = user.getJWT();
    public Object authenticateUser;
    

    
    //Se crea getAllUsers para mostrar todos los usuarios
    public List<User> getAllUsers() {
        try {
                //String token = User.getStoredToken();
                //System.out.println("Mi token inicio: " + token);
                //System.out.println("Mi token nuevo: " + authenticatedUserToken);
                
                //if (token == authenticatedUserToken && authenticatedUserToken != null){

                    
                            
                            //System.out.println("Mi token: " + token);
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
                        
                //}else{
                 //   System.out.println("Los tokens no son iguales");
                 //   return Collections.emptyList();
               // }
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
            String query = "INSERT INTO USUARIO SET Nombre = ?, Apellidos = ?, Email = ?, Nombre_Usuario = ?, Contraseña = ? ";
            jdbcTemplate.update(query, user.getName(),user.getLastnames(),user.getEmail(), user.getUsername(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Se crea el deleteUsuario para eliminar usuarios
    public int deleteUsuario(int id) {
        try {
                jdbcTemplate.update("DELETE FROM Notas WHERE ID_USUARIO = ?", id);
                    String query = "DELETE FROM USUARIO WHERE ID_Usuario = ?"; 
                    jdbcTemplate.update(query, id);
                    return 1;
                 
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //******** Notes ********
    
    //Todas las notas de todos

    public List<Note> getAllNotesAllUsers() {
        try {
               
                    String query = "SELECT * FROM Notas";
                    List<Map<String, Object>> resultDB = jdbcTemplate.queryForList(query);
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
                
                }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    
}

    public void insertNota(Note note) {
        try {
           String query = "INSERT INTO NOTAS SET ESTADO = ?,TITULO = ?, CONTENIDO = ?, ID_USUARIO = ? "; 
                jdbcTemplate.update(query, note.getStatus(),note.getTitle(),note.getContent(),note.getUserID());
                
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Notas incompletas
    public List<Note> getAllNotes(int idUser) {
        try {
            
                    String query = "SELECT * FROM Notas WHERE ESTADO = 0 AND ID_USUARIO =?;";
                    List<Map<String, Object>> resultDB = jdbcTemplate.queryForList(query, idUser);
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
           String query = "UPDATE NOTAS SET TITULO = ?, CONTENIDO = ? WHERE ID_NOTAS = ? AND ID_USUARIO =?";
                jdbcTemplate.update(query, note.getTitle(),note.getContent(), note.getNoteID() , note.getUserID());
                
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
            String query = "SELECT * FROM Usuario WHERE Nombre_Usuario = ? AND Contraseña = ?";
           
            User authenticatedUser = jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
                int UserID = (int)rs.getInt("ID_Usuario");
                String Name = rs.getString("Nombre");
                String Lastnames = rs.getString("Apellidos");
                String Email = rs.getString("Email");
                String Username = rs.getString("Nombre_Usuario");
                String Password = rs.getString("Contraseña");
                //User userauth = new User(UserID,Name,Lastnames,Email,Username,Password);
                //String token = userauth.getJWT();
                
                
                //String JWT = userAuth.setJTW();
                //this.authenticatedUserToken = token;
                //userauth.setJTW();
                return new User(UserID,Name,Lastnames,Email,Username,Password);
            }, username, password);
            authenticatedUser.setJTW();
            //authenticatedUser.setStoredToken(token);

            return authenticatedUser;

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("no encontrado");
            User user = new User();
            String token = user.getJWT();
            User.setStoredToken(token);
            return null;
        }
    }

    public boolean checkJWT(int id, String JWT) {
        try {
            String query = "SELECT * FROM Usuario WHERE ID_Usuario = ?";
           
            User authenticatedUser = jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
                int UserID = (int)rs.getInt("ID_Usuario");
                String Name = rs.getString("Nombre");
                String Lastnames = rs.getString("Apellidos");
                String Email = rs.getString("Email");
                String Username = rs.getString("Nombre_Usuario");
                String Password = rs.getString("Contraseña");

                return new User(UserID,Name,Lastnames,Email,Username,Password);
            }, id);
            String token = authenticatedUser.getJWT();
            
            if (token.equals(JWT)) {
                return true;
            }else{
                return false;
            }
            
            

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("no encontrado");
            return false;
        }
    }
}
