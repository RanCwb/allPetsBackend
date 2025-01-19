package com.pets.all_pets.repository;

import com.pets.all_pets.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {
    boolean existsByEmail(String email);

    CustomerModel findByEmail(String email);
}
