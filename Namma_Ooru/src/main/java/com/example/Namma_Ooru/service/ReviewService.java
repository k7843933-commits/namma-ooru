package com.example.Namma_Ooru.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Namma_Ooru.entity.Booking;
import com.example.Namma_Ooru.entity.Review;
import com.example.Namma_Ooru.repository.BookingRepository;
import com.example.Namma_Ooru.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public String addReview(int bookingId, Review review) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // ✅ Completed check
        if (!"COMPLETED".equals(booking.getStatus())) {
            throw new RuntimeException("Trip not completed yet");
        }

        // ✅ Same tourist check
        if (booking.getTourist().getId()!=(review.getTourist().getId())) {
            throw new RuntimeException("Unauthorized");
        }

        // ✅ Already reviewed
        if (reviewRepository.existsByBooking(booking)) {
            throw new RuntimeException("Review already submitted");
        }

        review.setBooking(booking);
        review.setGuide(booking.getGuide());
        review.setCreatedAt(LocalDateTime.now());

        reviewRepository.save(review);

        return "Review added successfully";
    }
}