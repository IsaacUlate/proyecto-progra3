package com.backend.backend;

//Se importan las librerías
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

    //Se crea getAllUsers para mostrar todos los usuarios
    public List<User> getAllUsers() {
        try {
            String query = "SELECT * FROM Usuario";
            List<Map<String, Object>> resultProducts = jdbcTemplate.queryForList(query);
            List<User> GetUsers = new ArrayList<>();

            for (Map<String, Object> row : resultProducts) {
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
}
