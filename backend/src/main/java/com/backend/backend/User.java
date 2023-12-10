package com.backend.backend;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service
public class User {
    //Variables para Usuarios
    private int UserID;
    private String Name;
    private String Lastnames;
    private String Email;
    private String Username;
    private String Password;
    private String JWT;
    private String JWTdb;
    private String mySecreString = "SECRET-KEY";
    private static String storedToken;
    
    

    @Autowired
    private JdbcTemplate jdbcTemplate;

    


    // Constructor sin parametros
    public User() {
        this.UserID = 0;
        this.Name = "";
        this.Lastnames = "";
        this.Email = "";
        this.Username = "";
        this.Password = "";
        this.JWT = "";

    }

    
    //Constructor de Usuarios
    public User(int userID, String name, String lastnames, String email, String username, String password) {
        UserID = userID;
        Name = name;
        Lastnames = lastnames;
        Email = email;
        Username = username;
        Password = password;
        this.JWT = generateJsonWebToken(username, password);
        setJTW();
        
    }

    // Constructor "vacio"
    public User(int userID, String name, String lastnames, String email) {
        UserID = userID;
        Name = name;
        Lastnames = lastnames;
        Email = email;
      
    }

    // Getters and Setters
    public int getUserID() {
        return UserID;
    }
    public void setUserID(int userID) {
        UserID = userID;
    }
    public boolean checkPassword(String password) {
        return this.Password.equals(password);
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getLastnames() {
        return Lastnames;
    }
    public void setLastnames(String lastnames) {
        Lastnames = lastnames;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        Username = username;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public void setJTW() {
        this.JWT = generateJsonWebToken(this.Username, this.Password);
    }  

    public String getJWT(){
        return JWT;
    }
    
    public static String getStoredToken() {
        return storedToken;
    }

    public static void setStoredToken(String token) {
        storedToken = token;
    }
    
    
    

    // Genera el JWT para encriptar contraseñas
        public String generateJsonWebToken(String username, String password) {
        try {
            // Concatenate username, password, and secret key
            String dataToHash = ""+username + password + mySecreString;
            System.out.println("jwt = " + username);
     
            // Use SHA-256 algorithm to hash the data
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));

            // Convert hashed bytes to a hexadecimal representation
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            System.out.println("jwt = " + hexString.toString());
     
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Handle the exception appropriately in a real-world scenario
        }
    }
}
