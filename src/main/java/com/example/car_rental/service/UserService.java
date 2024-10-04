package com.example.car_rental.service;

import com.example.car_rental.models.User;
import com.example.car_rental.payload.AuthenticationRequest;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    User saveUser(User user);
    void deleteUser(Long id);
    boolean validateUser(String username, String password);
    boolean registerUser(AuthenticationRequest request);
}