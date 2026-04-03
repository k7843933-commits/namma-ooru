package com.example.Namma_Ooru.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Namma_Ooru.entity.Guide;
import com.example.Namma_Ooru.service.GuideService;

@RestController
@RequestMapping("/guide")
public class GuideController {

    @Autowired
    private GuideService service;

    @PostMapping("/add")
    public Guide addGuide(@RequestBody Guide guide){
        return service.addGuide(guide);
    }

    @GetMapping("/all")
    public List<Guide> getAllGuides(){
        return service.getAllGuides();
    }

    @GetMapping("/{id}")
    public Guide getGuideById(@PathVariable int id){
        return service.getGuideById(id);
    }

    @PutMapping("/update/{id}")
    public Guide updateGuide(@PathVariable int id,@RequestBody Guide guide){
        return service.updateGuide(id, guide);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteGuide(@PathVariable int id){
        return service.deleteGuide(id);
    }
    @GetMapping("/city/{cityId}")
    public List<Guide> getGuidesByCity(@PathVariable int cityId){
        return service.getGuidesByCity(cityId);
    }
}