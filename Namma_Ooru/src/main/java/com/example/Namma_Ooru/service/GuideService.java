package com.example.Namma_Ooru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Namma_Ooru.entity.Guide;
import com.example.Namma_Ooru.repository.GuideRepository;

@Service
public class GuideService {

    @Autowired
    private GuideRepository repo;

    public Guide addGuide(Guide guide){
        return repo.save(guide);
    }

    public List<Guide> getAllGuides(){
        return repo.findAll();
    }

    public Guide getGuideById(int id){
        return repo.findById(id).orElse(null);
    }

    public Guide updateGuide(int id, Guide guide){
        Guide g = repo.findById(id).orElse(null);

        if(g != null){
            g.setName(guide.getName());
            g.setType(guide.getType());
            g.setPerHourAmount(guide.getPerHourAmount());
            return repo.save(g);
        }

        return null;
    }

    public String deleteGuide(int id){
        repo.deleteById(id);
        return "Guide deleted successfully";
    }
    public List<Guide> getGuidesByCity(int cityId){
        return repo.findByCityId(cityId);
    }
}