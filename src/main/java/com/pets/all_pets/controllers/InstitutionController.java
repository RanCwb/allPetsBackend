package com.pets.all_pets.controllers;


import com.pets.all_pets.DTO.InstitutionDTO;
import com.pets.all_pets.services.InstitutionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/institution")
public class InstitutionController {

    InstitutionService institutionService;

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService  = institutionService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createInstitution(@Valid @RequestBody InstitutionDTO institutionDTO) {
        try {
            institutionService.createInstitution(institutionDTO);
            return new ResponseEntity<>(institutionDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));        }
    }


}
