package com.backend.backend;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // Disable the default behavior of replacing the database with an embedded one
@ActiveProfiles("test")
class DatabaseServiceIntegrationTest {

    @Autowired
    private DatabaseService databaseService;

    //test unitario 1
    @Test
    void testGetAllUsersFromDatabase() {
        // llama la base de datos
        List<User> actualUsers = databaseService.getAllUsers();

        // usted puede afirmar el resultado en la base de datos actual 
        // puede añadir afirmaciones basadas en su base de datos actual de su test database
        
        assertTrue(actualUsers.size() >= 2, "Expected at least 2 records in the database, but found: " + actualUsers.size());
    }
   

}
