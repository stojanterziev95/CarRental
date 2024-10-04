package com.example.car_rental.controllers;

import com.example.car_rental.payload.AuthenticationRequest;
import com.example.car_rental.payload.AuthenticationResponse;
import com.example.car_rental.security.JwtUtil;
import com.example.car_rental.serviceImplementation.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final JwtUtil jwtUtil;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public AuthController(UserServiceImpl userServiceImpl, JwtUtil jwtUtil) {
        this.userServiceImpl = userServiceImpl;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        // Validate user credentials
        if (userServiceImpl.validateUser(username, password)) {
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(new AuthenticationResponse(token));
        } else {
            return ResponseEntity.status(401).body(new AuthenticationResponse("Invalid credentials"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthenticationRequest request) {
        boolean isRegistered = userServiceImpl.registerUser(request);
        if (isRegistered) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.status(400).body("User registration failed");
        }
    }
}
