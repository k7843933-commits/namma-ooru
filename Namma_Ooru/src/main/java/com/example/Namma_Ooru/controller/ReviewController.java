package com.example.Namma_Ooru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Namma_Ooru.entity.Review;
import com.example.Namma_Ooru.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add/{bookingId}")
    public ResponseEntity<String> addReview(
            @PathVariable int bookingId,
            @RequestBody Review review){

        return ResponseEntity.ok(
                reviewService.addReview(bookingId, review)
        );
    }
}