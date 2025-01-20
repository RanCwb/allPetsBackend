package com.pets.all_pets.DTO;
import jakarta.validation.constraints.NotBlank;

public class InstitutionDTO {
    private Integer idInstitution;

    @NotBlank(message = "CNPJ must not be blank")
    private String cnpj;

    @NotBlank(message = "Institution name must not be blank")
    private String institutionName;

    @NotBlank(message = "Responsible name must not be blank")
    private String responsibleName;

    @NotBlank(message = "Responsible phone must not be blank")
    private String responsiblePhone;

    @NotBlank(message = "Email must not be blank")
    private String email;

    private String address;

    private String token;

    private String password;

    private Boolean isPaying;

    public InstitutionDTO() {}

    public InstitutionDTO(Integer idInstitution, String cnpj, String institutionName, String responsibleName, String responsiblePhone, String email, String address, Boolean isPaying, String token) {
        this.idInstitution = idInstitution;
        this.cnpj = cnpj;
        this.institutionName = institutionName;
        this.responsibleName = responsibleName;
        this.responsiblePhone = responsiblePhone;
        this.email = email;
        this.address = address;
        this.token = token;
        this.isPaying = isPaying;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


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

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(Boolean isPaying) {
        this.isPaying = isPaying;
    }



}
