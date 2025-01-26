package com.pets.all_pets.controllers;

import com.pets.all_pets.DTO.AuthRequest;
import com.pets.all_pets.DTO.CustomerDTO;
import com.pets.all_pets.DTO.InstitutionDTO;
import com.pets.all_pets.DTO.TokenRenewRequest;
import com.pets.all_pets.config.JwtUtil;
import com.pets.all_pets.repository.CustomerRepository;
import com.pets.all_pets.repository.InstitutionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public final CustomerRepository customerRepository;
    private final InstitutionRepository institutionRepository;
    private final JwtUtil jwtUtil;
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);


    public AuthController(CustomerRepository customerRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, InstitutionRepository institutionRepository) {
        this.customerRepository = customerRepository;
        this.jwtUtil = jwtUtil;
        this.institutionRepository = institutionRepository;
    }


    @PostMapping("/login")
    public ResponseEntity<CustomerDTO> login(@RequestBody AuthRequest request) {
        var customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            customer.setToken(JwtUtil.generateToken(customer.getEmail()));
            customerRepository.save(customer);
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setIdCustomer(customer.getIdCustomer());
            customerDTO.setName(customer.getName());
            customerDTO.setEmail(customer.getEmail());
            customerDTO.setPassword(customer.getPassword());
            customerDTO.setPhone(customer.getPhone());
            customerDTO.setToken(customer.getToken());

            LOG.info("User logged in successfully and token generated");

            return ResponseEntity.ok(customerDTO);

        }
        throw new RuntimeException("Invalid credentials");
    }

    @PostMapping("/loginInstitution")
    public ResponseEntity<InstitutionDTO> loginInstitution(@RequestBody AuthRequest request) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        var institution = institutionRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(request.getPassword(), institution.getPassword())) {
            institution.setToken(JwtUtil.generateToken(institution.getEmail()));
            institutionRepository.save(institution);
            InstitutionDTO institutionDTO = new InstitutionDTO();
            institutionDTO.setIdInstitution(institution.getIdInstitution());
            institutionDTO.setInstitutionName(institution.getInstitutionName());
            institutionDTO.setResponsibleName(institution.getResponsibleName());
            institutionDTO.setResponsiblePhone(institution.getResponsiblePhone());
            institutionDTO.setCnpj(institution.getCnpj());
            institutionDTO.setEmail(institution.getEmail());
            institutionDTO.setAddress(institution.getAddress());
            institutionDTO.setIsPaying(institution.getIsPaying());
            institutionDTO.setPassword(passwordEncoder.encode(institution.getPassword()));
            institutionDTO.setToken(institution.getToken());
            LOG.info("Institution logged in successfully and token generated");
            return ResponseEntity.ok(institutionDTO);
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

