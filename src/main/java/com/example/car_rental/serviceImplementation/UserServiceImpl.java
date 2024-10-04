package com.example.car_rental.serviceImplementation;

import com.example.car_rental.payload.AuthenticationRequest;
import com.example.car_rental.models.User;
import com.example.car_rental.repository.UserRepository;
import com.example.car_rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        // You might want to add password encoding here
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }



    @Override
    public boolean validateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        // Check if user exists and the password matches
        if (user.isPresent()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            return passwordEncoder.matches(password, user.get().getPassword());
        } else {
            return false;
        }
    }

    @Override
    public boolean registerUser(AuthenticationRequest request) {
        // Check if username already exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return false; // Username is already taken
        }

        // Create new User instance
        User newUser = new User();
        newUser.setUsername(request.getUsername());

        // Encrypt the password before saving
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        // Save the user to the database
        userRepository.save(newUser);

        return true; // Registration successful
    }


}