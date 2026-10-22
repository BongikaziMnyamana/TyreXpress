
package za.ac.cput.service;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.*;
import za.ac.cput.repository.*;

import java.util.*;

@Service
public class AdminService implements IAdminService {

    private IAdminRepository repository;
    private IUserRepository userRepository;

    public AdminService(IAdminRepository repository, IUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public Admin create(Admin admin) {
        if (admin == null || admin.getUser() == null || admin.getUser().getUserId() == null) {
            throw new IllegalArgumentException("Admin must have a valid User");
        }

        User user = userRepository.findById(admin.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("User must have ADMIN role");
        }

        if (repository.findByUser_UserId(user.getUserId()).isPresent()) {
            throw new IllegalArgumentException("Admin already exists for this User");
        }

        Admin newAdmin = new Admin.Builder()
                .setUser(user)
                .build();

        return repository.save(newAdmin);
    }

    @Override
    public Admin read(Long adminId) {
        return repository.findById(adminId).orElse(null);
    }

    @Override
    public Admin update(Admin admin) {
        if (admin == null || admin.getAdminId() == null) {
            throw new IllegalArgumentException("Admin ID is required");
        }

        Admin existingAdmin = repository.findById(admin.getAdminId())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (admin.getUser() == null || admin.getUser().getUserId() == null) {
            throw new IllegalArgumentException("Admin must have a valid User");
        }

        User user = userRepository.findById(admin.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("User must have ADMIN role");
        }

        Admin linkedAdmin = repository.findByUser_UserId(user.getUserId()).orElse(null);

        if (linkedAdmin != null && !linkedAdmin.getAdminId().equals(existingAdmin.getAdminId())) {
            throw new IllegalArgumentException("User is already linked to another Admin");
        }

        Admin updatedAdmin = new Admin.Builder()
                .copy(existingAdmin)
                .setUser(user)
                .build();

        return repository.save(updatedAdmin);
    }

    @Override
    public boolean delete(Long adminId) {
        if (repository.existsById(adminId)) {
            repository.deleteById(adminId);
            return true;
        }
        return false;
    }

    @Override
    public List<Admin> getAll() {
        return repository.findAll();
    }

    @Override
    public Admin findByUserId(Long userId) {
        return repository.findByUser_UserId(userId).orElse(null);
    }
}
