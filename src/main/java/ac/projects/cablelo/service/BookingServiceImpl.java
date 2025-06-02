package ac.projects.cablelo.service;

import ac.projects.cablelo.model.Booking;
import ac.projects.cablelo.repository.BookingRepository;
import ac.projects.cablelo.repository.CabRepository;
import ac.projects.cablelo.repository.DriverRepository;
import ac.projects.cablelo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final CabRepository cabRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              UserRepository userRepository,
                              DriverRepository driverRepository,
                              CabRepository cabRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
        this.cabRepository = cabRepository;
    }

    @Override
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return ResponseEntity.ok(bookings);
    }

    @Override
    public ResponseEntity<Booking> getBookingById(String id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<String> createBooking(Booking booking) {
        // Validate user exists
        if (!userRepository.existsById(booking.getUserId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid User ID");
        }
        // Validate driver exists
        if (!driverRepository.existsById(booking.getDriverId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Driver ID");
        }
        // Validate cab exists and is available
        Optional<ac.projects.cablelo.model.Cab> cabOptional = cabRepository.findById(booking.getCabId());
        if (cabOptional.isEmpty() || !cabOptional.get().isAvailable()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cab is not available");
        }
        // Update cab availability to false
        ac.projects.cablelo.model.Cab cab = cabOptional.get();
        cab.setAvailable(false);
        cabRepository.save(cab);

        bookingRepository.save(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body("Booking created successfully");
    }

    @Override
    public ResponseEntity<String> updateBooking(String id, Booking booking) {
        Optional<Booking> existingBooking = bookingRepository.findById(id);
        if (existingBooking.isPresent()) {
            Booking updatedBooking = existingBooking.get();
            updatedBooking.setUserId(booking.getUserId());
            updatedBooking.setDriverId(booking.getDriverId());
            updatedBooking.setCabId(booking.getCabId());
            updatedBooking.setPickupLocation(booking.getPickupLocation());
            updatedBooking.setDropLocation(booking.getDropLocation());
            updatedBooking.setBookingTime(booking.getBookingTime());
            updatedBooking.setStatus(booking.getStatus());

            bookingRepository.save(updatedBooking);
            return ResponseEntity.ok("Booking updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
        }
    }

    @Override
    public ResponseEntity<String> deleteBooking(String id) {
        if (bookingRepository.existsById(id)) {
            // Optionally: Set the cab availability to true if booking is deleted
            Optional<Booking> bookingOpt = bookingRepository.findById(id);
            if (bookingOpt.isPresent()) {
                String cabId = bookingOpt.get().getCabId();
                cabRepository.findById(cabId).ifPresent(cab -> {
                    cab.setAvailable(true);
                    cabRepository.save(cab);
                });
            }

            bookingRepository.deleteById(id);
            return ResponseEntity.ok("Booking deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
        }
    }

    @Override
    public ResponseEntity<List<Booking>> findByUserId(String userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        if (bookings.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(bookings);
    }
}
