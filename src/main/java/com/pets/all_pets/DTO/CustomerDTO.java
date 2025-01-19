package com.pets.all_pets.DTO;

public class CustomerDTO {

    private Integer idCustomer;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String token;

    public CustomerDTO(Integer id, String name, String email, String password, String phone, String token) {
        this.idCustomer = id;
        this.name = name;
        this.email = email;
        this.token = token;
        this.password = password;
        this.phone = phone;
    }
    public CustomerDTO() {}

    public Integer getIdCustomer() {
        return idCustomer;
    }
    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }


}
