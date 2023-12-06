package com.backend.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

class DatabaseServiceTest {

    private DatabaseService databaseService;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate = mock(JdbcTemplate.class);
        databaseService = new DatabaseService(jdbcTemplate);
    }

    //test unitario detallado
    @Test
    void testGetAllUsers() {
        // Mocking the result from the database
        List<Map<String, Object>> mockResult = new ArrayList<>();
        mockResult.add(Map.of("ID_Usuario", 1, "Nombre", "", "Apellidos", "", "Email", "", "Nombre_Usuario", "", "Contraseña", ""));
        mockResult.add(Map.of("ID_Usuario", 2, "Nombre", "", "Apellidos", "", "Email", "", "Nombre_Usuario", "", "Contraseña", ""));


        // Mocking the behavior of jdbcTemplate.queryForList()
        when(jdbcTemplate.queryForList("SELECT * FROM Usuario")).thenReturn(mockResult);

        // Calling the actual method to be tested
        List<User> actualUsers = databaseService.getAllUsers();

        // Creating the expected result
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User(1, "", "", "", "", ""));
        expectedUsers.add(new User(2, "", "", "", "", ""));

        // Asserting that the actual result matches the expected result
        assertEquals(expectedUsers, actualUsers);
    }
   
}
