package com.example.car_rental.serviceImplementation;

import com.example.car_rental.exceptions.InvalidCredentialsException;
import com.example.car_rental.exceptions.UserNotFound;
import com.example.car_rental.payload.AuthenticationRequest;
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

    public boolean validateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return true;
            } else {
                throw new InvalidCredentialsException("Invalid username or password");
            }
        }
        throw new UserNotFound("User not found with username: " + username);
    }

    public boolean registerUser(AuthenticationRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new InvalidCredentialsException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return true;
    }
}