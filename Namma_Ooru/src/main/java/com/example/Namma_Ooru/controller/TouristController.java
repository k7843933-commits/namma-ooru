package com.example.Namma_Ooru.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.example.Namma_Ooru.entity.Tourist;
import com.example.Namma_Ooru.service.TouristService;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.ResponseEntity;
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/tourist")
public class TouristController {

    @Autowired
    private TouristService service;

    @PostMapping("/add")
    public Tourist addTourist(@Valid @RequestBody Tourist tourist){
        return service.addTourist(tourist);
    }

    @GetMapping("/all")
    public List<Tourist> getAllTourists(){
        return service.getAllTourists();
    }

    @GetMapping("/{id}")
    public Tourist getTouristById(@PathVariable int id){
        return service.getTouristById(id);
    }

    @PutMapping("/update")
    public Tourist updateTourist(@RequestBody Tourist tourist){
        return service.updateTourist(tourist);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTourist(@PathVariable int id){
        return service.deleteTourist(id);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex){
        String error = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body(error);
    }
    @GetMapping("/phone/{phone}")
    public Tourist getTouristByPhone(@PathVariable String phone){
        return service.getTouristByPhone(phone);
    }
}