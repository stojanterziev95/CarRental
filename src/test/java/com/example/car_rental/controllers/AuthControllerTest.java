package com.example.car_rental.controllers;

import com.example.car_rental.payload.AuthenticationRequest;
import com.example.car_rental.payload.AuthenticationResponse;
import com.example.car_rental.security.JwtUtil;
import com.example.car_rental.serviceImplementation.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private UserServiceImpl userServiceImpl;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loginSuccess() {
        // Arrange
        AuthenticationRequest request = new AuthenticationRequest("user", "password");
        when(userServiceImpl.validateUser("user", "password")).thenReturn(true);
        when(jwtUtil.generateToken("user")).thenReturn("token123");

        // Act
        ResponseEntity<AuthenticationResponse> response = authController.login(request);

        // Assert
        assertEquals("token123", response.getBody().getToken());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void loginFailure() {
        // Arrange
        AuthenticationRequest request = new AuthenticationRequest("user","wrongPassword");
        when(userServiceImpl.validateUser("user", "wrongPassword")).thenReturn(false);

        // Act
        ResponseEntity<AuthenticationResponse> response = authController.login(request);

        // Assert
        assertEquals("Invalid credentials", response.getBody().getToken());
        assertEquals(401, response.getStatusCodeValue());
    }

    @Test
    void registerSuccess() {
        // Arrange
        AuthenticationRequest request = new AuthenticationRequest("user", "password");
        when(userServiceImpl.registerUser(request)).thenReturn(true);

        // Act
        ResponseEntity<String> response = authController.register(request);

        // Assert
        assertEquals("User registered successfully", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void registerFailure() {
        // Arrange
        AuthenticationRequest request = new AuthenticationRequest("user", "password");
        when(userServiceImpl.registerUser(request)).thenReturn(false);

        // Act
        ResponseEntity<String> response = authController.register(request);

        // Assert
        assertEquals("User registration failed", response.getBody());
        assertEquals(400, response.getStatusCodeValue());
    }
}
