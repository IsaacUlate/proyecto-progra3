package com.backend.backend;

public class User {
    //Variables para Usuarios
    private int UserID;
    private String Name;
    private String Lastnames;
    private String Email;
    private String Username;
    private String Password;
    private String JWT;
    
    //Constructor de Usuarios
    public User(int userID, String name, String lastnames, String email, String username, String password) {
        UserID = userID;
        Name = name;
        Lastnames = lastnames;
        Email = email;
        Username = username;
        Password = password;
    }

    //Getters & Setters
    public int getUserID() {
        return UserID;
    }
    public void setUserID(int userID) {
        UserID = userID;
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

    

}
