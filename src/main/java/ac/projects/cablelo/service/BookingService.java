package ac.projects.cablelo.service;

import ac.projects.cablelo.model.Booking;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {
    ResponseEntity<List<Booking>> getAllBookings();
    ResponseEntity<Booking> getBookingById(String id);
    ResponseEntity<String> createBooking(Booking booking);
    ResponseEntity<String> updateBooking(String id, Booking booking);
    ResponseEntity<String> deleteBooking(String id);
    ResponseEntity<List<Booking>> findByUserId(String userId);
}
