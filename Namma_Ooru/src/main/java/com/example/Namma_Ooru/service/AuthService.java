package com.example.Namma_Ooru.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Namma_Ooru.entity.Tourist;
import com.example.Namma_Ooru.entity.Guide;
import com.example.Namma_Ooru.repository.TouristRepository;
import com.example.Namma_Ooru.repository.GuideRepository;
import com.example.Namma_Ooru.service.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private TouristRepository touristRepository;

    @Autowired
    private GuideRepository guideRepository;

    @Autowired
    private JwtUtil jwtUtil;   // ⚠️ small letter (important)

    // ✅ Tourist Login
    public String touristLogin(String phone, String password){

        if(phone == null || password == null){
            throw new RuntimeException("Phone or Password missing");
        }

        phone = phone.trim();
        password = password.trim();

        Optional<Tourist> tourist = touristRepository.findByPhoneAndPassword(phone, password);

        if(tourist.isPresent()){
            // ✅ JWT Token return
            return jwtUtil.generateToken(phone);
        } else {
            throw new RuntimeException("Invalid Tourist Credentials");
        }
    }

    // ✅ Guide Login
    public String guideLogin(String phone, String password){

        if(phone == null || password == null){
            throw new RuntimeException("Phone or Password missing");
        }

        phone = phone.trim();
        password = password.trim();

        Optional<Guide> guide = guideRepository.findByPhoneAndPassword(phone, password);

        if(guide.isPresent()){
            // ✅ JWT Token return
            return jwtUtil.generateToken(phone);
        } else {
            throw new RuntimeException("Invalid Guide Credentials");
        }
    }
}