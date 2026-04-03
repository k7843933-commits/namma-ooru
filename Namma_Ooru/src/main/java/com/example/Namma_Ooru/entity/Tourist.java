package com.example.Namma_Ooru.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Tourist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message="Name required")
    private String name;
    @Pattern(regexp = "^\\+91[6-9]\\d{9}$", message = "Enter valid Indian number (+91XXXXXXXXXX)")
    private String phone;
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    public Tourist(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // ✅ ADD THIS
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}