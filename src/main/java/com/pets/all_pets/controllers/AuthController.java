package com.pets.all_pets.controllers;

import com.pets.all_pets.DTO.AuthRequest;
import com.pets.all_pets.DTO.TokenRenewRequest;
import com.pets.all_pets.config.JwtUtil;
import com.pets.all_pets.repository.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public  final CustomerRepository customerRepository;
    private final JwtUtil jwtUtil;

    public AuthController(CustomerRepository customerRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        var customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            customer.setToken(JwtUtil.generateToken(customer.getEmail()));
            customerRepository.save(customer);
            return customer.getToken();
        }
        throw new RuntimeException("Invalid credentials");
    }

    @PostMapping("/renew")
    public String renewToken(@RequestBody TokenRenewRequest request) {
        if (!jwtUtil.validateToken(request.getOldToken())) {
            throw new RuntimeException("Invalid token");
        }
        String email = jwtUtil.extractEmail(request.getOldToken());
        return JwtUtil.generateToken(email);
    }
}

