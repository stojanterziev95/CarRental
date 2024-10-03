package com.example.car_rental.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminTest {

    @Test
    public void testAdminGettersAndSetters() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("password123");
        admin.setEmail("admin@example.com");
        admin.setRole(Role.ADMIN);

        assertEquals(1L, admin.getId());
        assertEquals("admin", admin.getUsername());
        assertEquals("password123", admin.getPassword());
        assertEquals("admin@example.com", admin.getEmail());
        assertEquals(Role.ADMIN, admin.getRole());
    }
}