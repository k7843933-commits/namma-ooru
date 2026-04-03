package com.example.Namma_Ooru.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Namma_Ooru.entity.city;
import com.example.Namma_Ooru.service.CityService;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService service;

    @PostMapping("/add")
    public city addCity(@RequestBody city city){
        return service.addCity(city);
    }

    @GetMapping("/all")
    public List<city> getAllCities(){
        return service.getAllCities();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCity(@PathVariable int id){
        return service.deleteCity(id);
    }
    @PutMapping("/update/{id}")
    public city updateCity(@PathVariable int id, @RequestBody city city){
        return service.updateCity(id, city);
    }
}