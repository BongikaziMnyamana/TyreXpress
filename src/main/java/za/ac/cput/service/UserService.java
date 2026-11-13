package za.ac.cput.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;
import za.ac.cput.domain.User;
import za.ac.cput.repository.*;

import java.util.*;

@Service
public class UserService implements IUserService {

    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User create(User user) {
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists.");
        }

        User encryptedUser = new User.Builder().copy(user).setpassword(passwordEncoder.encode(user.getPassword())).build();

        return repository.save(encryptedUser);
    }


    @Override
    public User read(Long userId) {
        return repository.findById(userId).orElse(null);
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    public boolean delete(Long userId) {
        if (repository.existsById(userId)) {
            repository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    public User login(String email, String password) {
        User user = repository.findByEmail(email).orElse(null);

        if (user == null) {
            return null;
        }

        String storedPassword = user.getPassword();

        // Existing BCrypt password
        if (storedPassword.startsWith("$2")) {
            return passwordEncoder.matches(password, storedPassword) ? user : null;
        }

        // Existing plain-text password
        if (storedPassword.equals(password)) {
            User updatedUser = new User.Builder().copy(user).setpassword(passwordEncoder.encode(password)).build();

            return repository.save(updatedUser);
        }

        return null;
    }
}
