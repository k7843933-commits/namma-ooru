package com.example.Namma_Ooru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Namma_Ooru.entity.Place;
import com.example.Namma_Ooru.repository.PlaceRepository;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository repo;

    public Place addPlace(Place place){
        return repo.save(place);
    }

    public List<Place> getAllPlaces(){
        return repo.findAll();
    }
    
    

    public Place getPlaceById(int id){
        return repo.findById(id).orElse(null);
    }

    public Place updatePlace(int id, Place place){
        Place p = repo.findById(id).orElse(null);

        if(p != null){
            p.setCity(place.getCity());
            p.setPlaceName(place.getPlaceName());
            return repo.save(p);
        }

        return null;
    }

    public String deletePlace(int id){
        repo.deleteById(id);
        return "Place deleted successfully";
    }
    public List<Place> getPlacesBycity(int cityId){
        return repo.findBycityId(cityId);
    }
   
}