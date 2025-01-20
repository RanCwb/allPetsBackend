package com.pets.all_pets.controllers;


import ch.qos.logback.classic.spi.ILoggingEvent;
import com.pets.all_pets.DTO.InstitutionDTO;
import com.pets.all_pets.services.InstitutionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getInstitutionById(@Valid @PathVariable(value = "id") Integer id, InstitutionDTO institutionDTO) {
        try {
            InstitutionDTO institution = institutionService.getInstitutionByIdl(id, institutionDTO);
            return new ResponseEntity<>(institution, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }

    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteInstitutionById(@Valid @PathVariable(value = "id") Integer id, InstitutionDTO institutionDTO) {
        try {
            InstitutionDTO institution = institutionService.deleteInstitutionById(id, institutionDTO);
            return  ResponseEntity.status(HttpStatus.OK).body(Map.of("Institution Deleted Successfully", institution));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

}
