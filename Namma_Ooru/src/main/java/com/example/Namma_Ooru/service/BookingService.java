package com.example.Namma_Ooru.service;

import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Namma_Ooru.entity.Booking;
import com.example.Namma_Ooru.entity.Guide;
import com.example.Namma_Ooru.entity.Tourist;
import com.example.Namma_Ooru.repository.BookingRepository;
import com.example.Namma_Ooru.repository.GuideRepository;
import com.example.Namma_Ooru.repository.TouristRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private GuideRepository guideRepository;

    @Autowired
    private TouristRepository touristRepository; // 🔥 NEW


    // ======================
    // ✅ ADD BOOKING
    // ======================
    public Booking addBooking(Booking booking){

        // 🔥 GET GUIDE
        Guide guide = guideRepository.findById(booking.getGuide().getId())
                .orElseThrow(() -> new RuntimeException("Guide not found"));

        // 🔥 GET TOURIST (MAIN FIX)
        Tourist tourist = touristRepository.findById(booking.getTourist().getId())
                .orElseThrow(() -> new RuntimeException("Tourist not found"));

        List<Booking> bookings = bookingRepository.findByGuideId(guide.getId());

        // ✅ Date clash check
        for(Booking b : bookings){

            if(!(booking.getEndDate().isBefore(b.getStartDate()) ||
                 booking.getStartDate().isAfter(b.getEndDate()))){

                throw new RuntimeException("Guide not available on this date");
            }
        }

        double total = booking.getHours() * guide.getPerHourAmount();

        booking.setGuide(guide);
        booking.setTourist(tourist); // 🔥 IMPORTANT FIX
        booking.setTotalAmount(total);
        booking.setStatus("BOOKED");

        return bookingRepository.save(booking);
    }


    // ======================
    // ✅ GET ALL BOOKINGS
    // ======================
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }


    // ======================
    // ✅ CANCEL BOOKING
    // ======================
    public Booking cancelBooking(int id, String reason){

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if("CANCELLED".equals(booking.getStatus())){
            throw new RuntimeException("Booking already cancelled");
        }

        if("COMPLETED".equals(booking.getStatus())){
            throw new RuntimeException("Cannot cancel completed booking");
        }

        if(reason == null || reason.trim().isEmpty()){
            throw new RuntimeException("Cancel reason required");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime bookingTime = booking.getStartDate().atStartOfDay();

        if(now.isAfter(bookingTime.minusHours(8))){
            throw new RuntimeException("Cannot cancel within 8 hours");
        }

        booking.setStatus("CANCELLED");
        booking.setCancelReason(reason);

        return bookingRepository.save(booking);
    }


    // ======================
    // ✅ COMPLETE BOOKING
    // ======================
    public Booking completeBooking(int id){

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if("CANCELLED".equals(booking.getStatus())){
            throw new RuntimeException("Cancelled booking cannot be completed");
        }

        booking.setStatus("COMPLETED");

        return bookingRepository.save(booking);
    }


    // ======================
    // ✅ GET BOOKINGS BY GUIDE
    // ======================
    public List<Booking> getBookingsByGuide(int guideId){
        return bookingRepository.findByGuideId(guideId);
    }


    // ======================
    // ✅ GET BOOKINGS BY TOURIST
    // ======================
    public List<Booking> getBookingsByTourist(int touristId){

        autoCompleteBookings(); // 🔥 ADD THIS LINE

        return bookingRepository.findByTouristId(touristId);
    }
 // 🔥 AUTO COMPLETE BOOKINGS
 // ======================
 public void autoCompleteBookings() {

     List<Booking> bookings = bookingRepository.findAll();

     for (Booking b : bookings) {

         if ("BOOKED".equals(b.getStatus()) &&
             b.getEndDate().isBefore(LocalDate.now())) {

             b.setStatus("COMPLETED");
             bookingRepository.save(b);
         }
     }
 }


    // ======================
    // ✅ DELETE BOOKING
    // ======================
    public void deleteBooking(int id){
        bookingRepository.deleteById(id);
    }

}