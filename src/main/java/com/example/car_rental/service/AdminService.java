package com.example.car_rental.service;

import com.example.car_rental.models.Admin;
import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmins();
    Admin getAdminById(Long id);
    Admin createAdmin(Admin admin);
    Admin updateAdmin(Long id, Admin admin);
    void deleteAdmin(Long id);
    Admin getAdminByUsername(String username);
}
