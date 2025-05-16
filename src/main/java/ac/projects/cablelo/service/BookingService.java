package ac.projects.cablelo.service;

import ac.projects.cablelo.model.Booking;
import ac.projects.cablelo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Booking getBookingById(String id) {
        return bookingRepository.findById(id).orElse(null);
    }
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
    public void updateBooking(String id ,Booking newbooking) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            newbooking.setId(id);
            bookingRepository.save(newbooking);
        }else {
            System.out.println("Booking not found");
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
