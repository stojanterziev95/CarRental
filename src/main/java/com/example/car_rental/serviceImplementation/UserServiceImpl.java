package com.example.car_rental.serviceImplementation;

import com.example.car_rental.models.AuthenticationRequest;
import com.example.car_rental.models.User;
import com.example.car_rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Validate user credentials against the database
    public boolean validateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false; // User not found
    }

    // Handle user registration
    public boolean registerUser(AuthenticationRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return false; // Username already exists
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Hash the password
        userRepository.save(user);
        return true; // User registered successfully
    }
}