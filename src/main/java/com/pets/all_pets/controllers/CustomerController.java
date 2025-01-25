package com.pets.all_pets.controllers;
import com.pets.all_pets.DTO.CustomerDTO;
import com.pets.all_pets.exceptions.CustomerValidationException;
import com.pets.all_pets.services.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    public CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customer) {
        try {
            customerService.createCustomer(customer);
            return new ResponseEntity<>(customer, HttpStatus.CREATED);

        } catch (CustomerValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An unexpected error occurred."));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable(value = "id") Integer id, @RequestBody CustomerDTO customer) {
        try {
            CustomerDTO updatedCustomer = customerService.updateCustomer(id, customer);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } catch (CustomerValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An unexpected error occurred."));
        }
    }

   @GetMapping("/getAll")
    public ResponseEntity<Page<CustomerDTO>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<CustomerDTO> customers = customerService.getAllCustomers(page, size);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable(value = "id") Integer id,
                                             @RequestHeader("Authorization") String authHeader) {
        try {
            if (!authHeader.startsWith("Bearer ")) {
                throw new IllegalArgumentException("Invalid Authorization header");
            }

            String token = authHeader.substring(7);
            CustomerDTO customer = customerService.getCustomerById(id, token);

            return new ResponseEntity<>(customer, HttpStatus.OK);

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCustomerById(@PathVariable(value = "id") Integer id) {
        try {
            CustomerDTO customer = customerService.deleteCustomerById(id, null);
            return new ResponseEntity<>(Map.of("Customer Deleted Successfully", customer), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
