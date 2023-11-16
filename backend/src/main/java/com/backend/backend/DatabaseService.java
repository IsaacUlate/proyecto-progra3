package com.backend.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isDatabaseConnected() {
        try {
            jdbcTemplate.queryForObject("SELECT 1 FROM DUAL", Integer.class);
            return true;
        } catch (Exception e) {
            return false;
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

    
}
