package com.backend.backend;

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

    public List<User> getAllUsers() {
        try {
            // Replace 'app_log' with your actual table name and adjust the query as needed
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
            // Handle exceptions if needed
            e.printStackTrace();
            return null;
        }
    }


    public void insertUsuario(User user) {
        try {
            String query = "INSERT USUARIO SET Nombre = ?, Apellidos = ?, Email = ?, Nombre_Usuario = ?, Contraseña = ? ";
            jdbcTemplate.update(query, user.getName(),user.getLastnames(),user.getEmail(), user.getUsername(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions if needed
        }
    }

    public int deleteUsuario(int id) {
        try {
            String query = "DELETE FROM USUARIO WHERE ID_Usuario = ?";
            jdbcTemplate.update(query, id);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
            // Handle exceptions if needed
        }
    }

   

    
}
