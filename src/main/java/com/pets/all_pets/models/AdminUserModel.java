package com.pets.all_pets.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class AdminUserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 255, unique = true)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_institution", nullable = false)
    private InstitutionModel institution;

    public AdminUserModel() {}

    public AdminUserModel(Integer idUser, InstitutionModel institution, String password, String email, String name) {
        this.idUser = idUser;
        this.institution = institution;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    @Override
    public String toString() {
        return "AdminUserModel{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", institution=" + institution +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AdminUserModel that = (AdminUserModel) o;
        return Objects.equals(idUser, that.idUser) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(institution, that.institution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, name, email, password, institution);
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public InstitutionModel getInstitution() {
        return institution;
    }

    public void setInstitution(InstitutionModel institution) {
        this.institution = institution;
    }
}
