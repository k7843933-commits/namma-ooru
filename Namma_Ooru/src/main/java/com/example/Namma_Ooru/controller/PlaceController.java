package com.example.Namma_Ooru.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Namma_Ooru.entity.Place;
import com.example.Namma_Ooru.service.PlaceService;

@RestController
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private PlaceService service;

    @PostMapping("/add")
    public Place addPlace(@RequestBody Place place){
        return service.addPlace(place);
    }

    @GetMapping("/all")
    public List<Place> getAllPlaces(){
        return service.getAllPlaces();
    }

    @GetMapping("/city/{cityId}")
    public List<Place> getPlacesBycity(@PathVariable int cityId){
        return service.getPlacesBycity(cityId);
    }

    @PutMapping("/update/{id}")
    public Place updatePlace(@PathVariable int id,@RequestBody Place place){
        return service.updatePlace(id, place);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePlace(@PathVariable int id){
        return service.deletePlace(id);
    }
}