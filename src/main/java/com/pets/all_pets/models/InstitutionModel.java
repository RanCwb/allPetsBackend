package com.pets.all_pets.models;
import com.pets.all_pets.DTO.InstitutionDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "institutions")
public class InstitutionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInstitution;

    @Column(nullable = false, length = 20)
    private String cnpj;

    @Column(nullable = false, length = 255)
    private String institutionName;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "Responsible name must not be blank")
    private String responsibleName;

    @Column(nullable = false, length = 20)
    private String responsiblePhone;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column
    private Boolean isPaying;

    @Column
    private String token;


    @Override
    public String toString() {
        return "InstitutionModel{" +
                "idInstitution=" + idInstitution +
                ", cnpj='" + cnpj + '\'' +
                ", institutionName='" + institutionName + '\'' +
                ", responsibleName='" + responsibleName + '\'' +
                ", responsiblePhone='" + responsiblePhone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", isPaying=" + isPaying +
                ", adminUsers=" + adminUsers +
                ", animals=" + animals +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public InstitutionModel() {
    }

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdminUserModel> adminUsers;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AnimalModel> animals;


    public Integer getIdInstitution() {
        return idInstitution;
    }

    public void setIdInstitution(Integer idInstitution) {
        this.idInstitution = idInstitution;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getResponsiblePhone() {
        return responsiblePhone;
    }

    public void setResponsiblePhone(String responsiblePhone) {
        this.responsiblePhone = responsiblePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getPaying() {
        return isPaying;
    }

    public void setPaying(Boolean paying) {
        isPaying = paying;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<AdminUserModel> getAdminUsers() {
        return adminUsers;
    }

    public void setAdminUsers(List<AdminUserModel> adminUsers) {
        this.adminUsers = adminUsers;
    }

    public List<AnimalModel> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalModel> animals) {
        this.animals = animals;
    }

    public void setIsPaying(Boolean isPaying) {
        this.isPaying = isPaying;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public InstitutionModel(Integer idInstitution, List<AnimalModel> animals, List<AdminUserModel> adminUsers, Boolean isPaying, String address, String email, String responsiblePhone, String responsibleName, String institutionName, String cnpj, String password, String token) {
        this.idInstitution = idInstitution;
        this.animals = animals;
        this.adminUsers = adminUsers;
        this.isPaying = isPaying;
        this.token = token;
        this.address = address;
        this.email = email;
        this.responsiblePhone = responsiblePhone;
        this.responsibleName = responsibleName;
        this.institutionName = institutionName;
        this.cnpj = cnpj;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InstitutionModel that = (InstitutionModel) o;
        return Objects.equals(idInstitution, that.idInstitution) && Objects.equals(cnpj, that.cnpj) && Objects.equals(institutionName, that.institutionName) && Objects.equals(responsibleName, that.responsibleName) && Objects.equals(responsiblePhone, that.responsiblePhone) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(address, that.address) && Objects.equals(isPaying, that.isPaying) && Objects.equals(token, that.token) && Objects.equals(adminUsers, that.adminUsers) && Objects.equals(animals, that.animals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInstitution, cnpj, institutionName, responsibleName, responsiblePhone, password, email, address, isPaying, token, adminUsers, animals);
    }

    public Boolean getIsPaying() {
        return isPaying;
    }


    public Object orElseThrow(Object userNotFound) {
        return null;
    }
}
