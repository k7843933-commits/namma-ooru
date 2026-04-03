package com.example.Namma_Ooru.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Namma_Ooru.entity.Booking;
import com.example.Namma_Ooru.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestController
@RequestMapping("/booking")
public class BookingController {
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleError(RuntimeException ex){
	    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@Autowired
    private BookingService service;

    @PostMapping("/add")
    public Booking addBooking(@RequestBody Booking booking){
        return service.addBooking(booking);
    }

    @GetMapping("/all")
    public List<Booking> getAllBookings(){
        return service.getAllBookings();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBooking(@PathVariable int id){
         service.deleteBooking(id);
         return "Booking deleted Succesfully";
    }
    @GetMapping("/guide/{guideId}")
    public List<Booking> getBookingsByGuide(@PathVariable int guideId){
        return service.getBookingsByGuide(guideId);
    }
    @GetMapping("/tourist/{touristId}")
    public List<Booking> getBookingsByTourist(@PathVariable int touristId){
        return service.getBookingsByTourist(touristId);
    }
    @PutMapping("/cancel/{id}")
    public Booking cancelBooking(@PathVariable int id, @RequestBody Map<String, String> body){
        return service.cancelBooking(id, body.get("cancelReason"));
    }
    @PutMapping("/complete/{id}")
    public Booking completeBooking(@PathVariable int id){
        return service.completeBooking(id);
    }
}
