package ac.projects.cablelo.controller;

import ac.projects.cablelo.model.Booking;
import ac.projects.cablelo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable String id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<String> createBooking(@PathVariable String userId,@RequestBody Booking booking) {
        return bookingService.createBooking(booking, userId);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBooking(@PathVariable String id, @RequestBody Booking booking) {
        return bookingService.updateBooking(id, booking);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable String id) {
        return bookingService.deleteBooking(id);
    }
}
