package ac.projects.cablelo.service;

import ac.projects.cablelo.model.Booking;
import ac.projects.cablelo.model.Driver;
import ac.projects.cablelo.model.User;
import ac.projects.cablelo.repository.BookingRepository;
import ac.projects.cablelo.repository.DriverRepository;
import ac.projects.cablelo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private DriverService driverService;

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
    @Transactional
    public ResponseEntity<String> createBooking(Booking booking ) {

        String id=booking.getId();
        Optional<Booking> v=bookingRepository.findById(id);
        if(v.isPresent()) {
            return new ResponseEntity<>("Booking already exists",HttpStatus.CONFLICT);
        }
        String userId = booking.getUserId();
        String driverId = booking.getDriverId();
        User user= userService.getUserById(userId).getBody();
        Driver driver=driverService.getDriverById(driverId).getBody();
        if(user==null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        Booking book = bookingRepository.save(booking);
        driver.getBookingList().add(book);
        user.getBookings().add(book);
        userRepository.save(user);
        driverRepository.save(driver);
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
    @Transactional
    public ResponseEntity<String> deleteBooking(String id) {
        try{
            Optional<Booking> book=bookingRepository.findById(id);
            if(book.isPresent()) {
                String userId = book.get().getUserId();
                String driverId = book.get().getDriverId();
                User user = userService.getUserById(userId).getBody();
                Driver driver = driverService.getDriverById(driverId).getBody();
                user.getBookings().remove(book.get());
                driver.getBookingList().remove(book.get());

                userRepository.save(user);

                driverRepository.save(driver);

                bookingRepository.delete(book.get());
                return new ResponseEntity<>("Booking Deleted ", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Booking Not Found ", HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(e +"", HttpStatus.NOT_FOUND);
        }
    }

}
