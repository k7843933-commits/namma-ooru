package com.example.Namma_Ooru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Namma_Ooru.entity.Booking;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByGuideId(int guideId);
    List<Booking> findByTouristId(int touristId);
}