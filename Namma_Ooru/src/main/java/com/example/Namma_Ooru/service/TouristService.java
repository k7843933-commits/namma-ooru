package com.example.Namma_Ooru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Namma_Ooru.entity.Tourist;
import com.example.Namma_Ooru.repository.TouristRepository;

@Service
public class TouristService {
	

    @Autowired
    private TouristRepository repo;
    

    public Tourist addTourist(Tourist tourist){
        return repo.save(tourist);
    }

    public List<Tourist> getAllTourists(){
        return repo.findAll();
    }

    public Tourist getTouristById(int id){
        return repo.findById(id).orElse(null);
    }

    public Tourist updateTourist(Tourist tourist){
        return repo.save(tourist);
    }

    public String deleteTourist(int id){
        repo.deleteById(id);
        return "Tourist deleted successfully";
    }
    public Tourist getTouristByPhone(String phone){
        return repo.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("Tourist not found"));
    }
}