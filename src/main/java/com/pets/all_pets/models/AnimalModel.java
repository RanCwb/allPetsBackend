package com.pets.all_pets.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
@Entity
public class AnimalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnimal;

    @Column(nullable = false, length = 255)
    private String name;

    private Integer age;

    private String species;

    private String gender;

    private Double weight;

    private Boolean availableForAdoption;

    private Integer parentage;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_institution", nullable = false)
    private InstitutionModel institution;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<VaccineModel> vaccines;

    @Override
    public String toString() {
        return "AnimalModel{" +
                "idAnimal=" + idAnimal +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", species='" + species + '\'' +
                ", gender='" + gender + '\'' +
                ", weight=" + weight +
                ", availableForAdoption=" + availableForAdoption +
                ", parentage=" + parentage +
                ", description='" + description + '\'' +
                ", institution=" + institution +
                ", vaccines=" + vaccines +
                '}';
    }

    public AnimalModel() {}

    public AnimalModel(Integer idAnimal, List<VaccineModel> vaccines, InstitutionModel institution, String description, Integer parentage, Boolean availableForAdoption, String gender, String species, Integer age, String name, Double weight) {
        this.idAnimal = idAnimal;
        this.vaccines = vaccines;
        this.institution = institution;
        this.description = description;
        this.parentage = parentage;
        this.availableForAdoption = availableForAdoption;
        this.gender = gender;
        this.species = species;
        this.age = age;
        this.name = name;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AnimalModel that = (AnimalModel) o;
        return Objects.equals(idAnimal, that.idAnimal) && Objects.equals(name, that.name) && Objects.equals(age, that.age) && Objects.equals(species, that.species) && Objects.equals(gender, that.gender) && Objects.equals(weight, that.weight) && Objects.equals(availableForAdoption, that.availableForAdoption) && Objects.equals(parentage, that.parentage) && Objects.equals(description, that.description) && Objects.equals(institution, that.institution) && Objects.equals(vaccines, that.vaccines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnimal, name, age, species, gender, weight, availableForAdoption, parentage, description, institution, vaccines);
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public List<VaccineModel> getVaccines() {
        return vaccines;
    }

    public void setVaccines(List<VaccineModel> vaccines) {
        this.vaccines = vaccines;
    }

    public InstitutionModel getInstitution() {
        return institution;
    }

    public void setInstitution(InstitutionModel institution) {
        this.institution = institution;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParentage() {
        return parentage;
    }

    public void setParentage(Integer parentage) {
        this.parentage = parentage;
    }

    public Boolean getAvailableForAdoption() {
        return availableForAdoption;
    }

    public void setAvailableForAdoption(Boolean availableForAdoption) {
        this.availableForAdoption = availableForAdoption;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
