package service;

import models.Admin;
import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmins();
    Admin getAdminById(Long id);
    Admin createAdmin(Admin admin);
    Admin updateAdmin(Long id, Admin admin);
    void deleteAdmin(Long id);
}
