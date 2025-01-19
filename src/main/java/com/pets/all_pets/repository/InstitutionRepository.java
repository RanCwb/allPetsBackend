package com.pets.all_pets.repository;
import com.pets.all_pets.models.InstitutionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<InstitutionModel, Integer> {

    boolean existsByEmail(String email);


}