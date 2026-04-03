package com.example.Namma_Ooru.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Namma_Ooru.entity.city;
import com.example.Namma_Ooru.repository.CityRepository;

@Service
public class CityService {

    @Autowired
    private CityRepository repo;

    public city addCity(city city){
        return repo.save(city);
    }

    public List<city> getAllCities(){
        return repo.findAll();
    }

    public String deleteCity(int id){
        repo.deleteById(id);
        return "City deleted successfully";
    }
    public city updateCity(int id, city city) {
        city existingCity = repo.findById(id).orElse(null);

        if(existingCity != null){
            existingCity.setCityName(city.getCityName());
            return repo.save(existingCity);
        }

        return null;
    }
}