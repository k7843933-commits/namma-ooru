package com.example.Namma_Ooru.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Guide {
	private String phone;
	private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String type;

    private double perHourAmount;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonBackReference
    private city city;

    public Guide(){}

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPerHourAmount() {
        return perHourAmount;
    }

    public void setPerHourAmount(double perHourAmount) {
        this.perHourAmount = perHourAmount;
    }

    public city getCity() {
        return city;
    }

    public void setCity(city city) {
        this.city = city;
    }
}