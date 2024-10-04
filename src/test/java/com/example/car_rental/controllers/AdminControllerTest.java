package com.example.car_rental.controllers;

import com.example.car_rental.models.Admin;
import com.example.car_rental.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAdmins() {
        // Arrange
        List<Admin> expectedAdmins = Arrays.asList(new Admin(), new Admin());
        when(adminService.getAllAdmins()).thenReturn(expectedAdmins);

        // Act
        List<Admin> actualAdmins = adminController.getAllAdmins();

        // Assert
        assertEquals(expectedAdmins, actualAdmins);
        verify(adminService, times(1)).getAllAdmins();
    }

    @Test
    void getAdminById() {
        // Arrange
        Admin expectedAdmin = new Admin();
        when(adminService.getAdminById(1L)).thenReturn(expectedAdmin);

        // Act
        ResponseEntity<Admin> response = adminController.getAdminById(1L);

        // Assert
        assertEquals(expectedAdmin, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(adminService, times(1)).getAdminById(1L);
    }

    @Test
    void createAdmin() {
        // Arrange
        Admin newAdmin = new Admin();
        when(adminService.createAdmin(newAdmin)).thenReturn(newAdmin);

        // Act
        Admin createdAdmin = adminController.createAdmin(newAdmin);

        // Assert
        assertEquals(newAdmin, createdAdmin);
        verify(adminService, times(1)).createAdmin(newAdmin);
    }

    @Test
    void updateAdmin() {
        // Arrange
        Admin updatedAdmin = new Admin();
        when(adminService.updateAdmin(1L, updatedAdmin)).thenReturn(updatedAdmin);

        // Act
        Admin actualUpdatedAdmin = adminController.updateAdmin(1L, updatedAdmin);

        // Assert
        assertEquals(updatedAdmin, actualUpdatedAdmin);
        verify(adminService, times(1)).updateAdmin(1L, updatedAdmin);
    }

    @Test
    void deleteAdmin() {
        // Act
        ResponseEntity<Void> response = adminController.deleteAdmin(1L);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(adminService, times(1)).deleteAdmin(1L);
    }

    @Test
    void loginSuccess() {
        // Arrange
        Admin loginDetails = new Admin();
        loginDetails.setUsername("admin");
        loginDetails.setPassword("password");

        Admin adminFromDb = new Admin();
        adminFromDb.setUsername("admin");
        adminFromDb.setPassword("encodedPassword");

        when(adminService.getAdminByUsername("admin")).thenReturn(adminFromDb);
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);

        // Act
        ResponseEntity<String> response = adminController.login(loginDetails);

        // Assert
        assertEquals("Login successful", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void loginFailure() {
        // Arrange
        Admin loginDetails = new Admin();
        loginDetails.setUsername("admin");
        loginDetails.setPassword("wrongPassword");

        Admin adminFromDb = new Admin();
        adminFromDb.setUsername("admin");
        adminFromDb.setPassword("encodedPassword");

        when(adminService.getAdminByUsername("admin")).thenReturn(adminFromDb);
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        // Act
        ResponseEntity<String> response = adminController.login(loginDetails);

        // Assert
        assertEquals("Invalid credentials", response.getBody());
        assertEquals(401, response.getStatusCodeValue());
    }
}

