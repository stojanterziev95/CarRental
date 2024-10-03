package com.example.car_rental.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testUserGettersAndSetters() {
        User user = new User();
        user.setId(1L);
        user.setUsername("user1");
        user.setPassword("password123");

        assertEquals(1L, user.getId());
        assertEquals("user1", user.getUsername());
        assertEquals("password123", user.getPassword());
    }
}