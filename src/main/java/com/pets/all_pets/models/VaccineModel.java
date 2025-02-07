package com.pets.all_pets.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "vaccines")
public class VaccineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVaccine;

    @ManyToOne
    @JoinColumn(name = "id_animal", nullable = false)
    private AnimalModel animal;

    @Column(name = "vaccine_name", nullable = false, length = 255)
    private String vaccineName;

    @Column(name = "vaccine_date")
    private LocalDate vaccineDate;

    @Column(name = "observations", columnDefinition = "TEXT")
    private String observations;

    public VaccineModel() {
    }

    public VaccineModel(Integer idVaccine, AnimalModel animal, String vaccineName, LocalDate vaccineDate, String observations) {
        this.idVaccine = idVaccine;
        this.animal = animal;
        this.vaccineName = vaccineName;
        this.vaccineDate = vaccineDate;
        this.observations = observations;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VaccineModel that = (VaccineModel) o;
        return Objects.equals(idVaccine, that.idVaccine) && Objects.equals(animal, that.animal) && Objects.equals(vaccineName, that.vaccineName) && Objects.equals(vaccineDate, that.vaccineDate) && Objects.equals(observations, that.observations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVaccine, animal, vaccineName, vaccineDate, observations);
    }

    public Integer getIdVaccine() {
        return idVaccine;
    }

    public void setIdVaccine(Integer idVaccine) {
        this.idVaccine = idVaccine;
    }

    public AnimalModel getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalModel animal) {
        this.animal = animal;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public LocalDate getVaccineDate() {
        return vaccineDate;
    }

    public void setVaccineDate(LocalDate vaccineDate) {
        this.vaccineDate = vaccineDate;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
