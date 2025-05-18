package ac.projects.cablelo.service;

import ac.projects.cablelo.model.Booking;
import ac.projects.cablelo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> ls = bookingRepository.findAll();
        if(ls.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(ls,HttpStatus.OK);
        }


    }
    public ResponseEntity<Booking> getBookingById(String id) {
        Optional<Booking> v=bookingRepository.findById(id);
        if(v.isPresent()) {
            return new ResponseEntity<>(v.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    public ResponseEntity<String> createBooking(Booking booking) {
        bookingRepository.save(booking);
        return new ResponseEntity<>("Booking created with ID "+booking.getId(), HttpStatus.CREATED);
    }
    public ResponseEntity<List<Booking>> findByUserId(@PathVariable String userId) {
        List<Booking> ls = bookingRepository.findByUserId(userId);
        if(ls.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{

            return new ResponseEntity<>(ls,HttpStatus.OK);

        }
    }

    public ResponseEntity<String> updateBooking(String id ,Booking newbooking) {

        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            newbooking.setId(id);
            bookingRepository.save(newbooking);
            return new ResponseEntity<>("Booking Updated ", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Booking not Found ", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> deleteBooking(String id) {
        Optional<Booking> book=bookingRepository.findById(id);
        if(book.isPresent()) {
            bookingRepository.delete(book.get());
            return new ResponseEntity<>("Booking Deleted ", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Booking Not Found ", HttpStatus.NOT_FOUND);
        }
    }

}
