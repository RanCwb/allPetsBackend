package com.pets.all_pets.models;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class InstitutionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInstitution;

    @Column(nullable = false, length = 20)
    private String cnpj;

    @Column(nullable = false, length = 255)
    private String institutionName;

    private String responsibleName;

    private String responsiblePhone;

    private String email;

    private String address;

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
                '}';
    }

    private Boolean isPaying;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL)
    private List<AdminUserModel> adminUsers;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL)
    private List<AnimalModel> animals;

    // Getters e Setters
}
