package ac.projects.cablelo.service;

import ac.projects.cablelo.model.Booking;
import ac.projects.cablelo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();


    }
    public Object getBookingsByUserId(String userId) {
        List<Booking> lb=bookingRepository.findByUserId(userId);
        if(lb.size()>0) {
            return lb;
        }else{
            System.out.println();
            return "No Bookings Found";
        }

    }
    public Object getBookingById(String id) {
        Optional<Booking> booking=bookingRepository.findById(id);
        if(booking.isPresent()) {
            return booking.get();
        }else{
            return "Booking Not Found";
        }

    }
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
    public Object updateBooking(String id ,Booking newbooking) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            newbooking.setId(id);
            bookingRepository.save(newbooking);
            return new ResponseEntity<>("Booking Updated ", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Booking not Found ", HttpStatus.NOT_FOUND);
        }
    }

    public void deleteBooking(String id) {
        Optional<Booking> book=bookingRepository.findById(id);
        if(book.isPresent()) {
            bookingRepository.delete(book.get());
            System.out.println("Booking deleted");
        }else{
            System.out.println("Booking not found");
        }
    }

}
