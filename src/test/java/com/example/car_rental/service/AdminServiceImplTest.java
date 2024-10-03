package com.example.car_rental.service;

import com.example.car_rental.models.Admin;
import com.example.car_rental.repository.AdminRepository;
import com.example.car_rental.serviceImplementation.AdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdminServiceImplTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private AdminRepository adminRepository;

    private Admin admin;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        admin = new Admin();
        admin.setId(1L);
        admin.setUsername("admin");
    }

    @Test
    public void testGetAllAdmins() {
        when(adminRepository.findAll()).thenReturn(Collections.singletonList(admin));
        assertEquals(1, adminService.getAllAdmins().size());
        verify(adminRepository, times(1)).findAll();
    }

    @Test
    public void testGetAdminById() {
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        assertEquals(admin, adminService.getAdminById(1L));
        verify(adminRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateAdmin() {
        when(adminRepository.save(admin)).thenReturn(admin);
        assertEquals(admin, adminService.createAdmin(admin));
        verify(adminRepository, times(1)).save(admin);
    }

    @Test
    public void testUpdateAdmin() {
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        when(adminRepository.save(admin)).thenReturn(admin);

        Admin updatedAdmin = new Admin();
        updatedAdmin.setUsername("newAdmin");
        adminService.updateAdmin(1L, updatedAdmin);

        assertEquals("newAdmin", admin.getUsername());
        verify(adminRepository, times(1)).save(admin);
    }

    @Test
    public void testDeleteAdmin() {
        adminService.deleteAdmin(1L);
        verify(adminRepository, times(1)).deleteById(1L);
    }
}