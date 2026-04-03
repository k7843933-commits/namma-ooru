package com.example.Namma_Ooru.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int guideId;

    private String placeName;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonBackReference
    private city city;

   

    public Place(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public city getCity() {
        return city;
    }

    public void setCity(city city) {
        this.city = city;
    }

    
}