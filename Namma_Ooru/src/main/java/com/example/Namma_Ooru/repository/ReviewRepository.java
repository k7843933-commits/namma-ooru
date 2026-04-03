package com.example.Namma_Ooru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Namma_Ooru.entity.Review;
import com.example.Namma_Ooru.entity.Booking;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    boolean existsByBooking(Booking booking);

}