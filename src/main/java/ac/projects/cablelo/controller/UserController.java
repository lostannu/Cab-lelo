package ac.projects.cablelo.controller;
import ac.projects.cablelo.model.Booking;
import ac.projects.cablelo.model.User;
import ac.projects.cablelo.repository.BookingRepository;
import ac.projects.cablelo.service.BookingService;
import ac.projects.cablelo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private BookingRepository bookingRepository;

    private BookingService bookingService;

    private UserService userService;

    @Autowired
    public UserController(UserService userService, BookingService bookingService, BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @GetMapping("/{userId}/bookings")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable String userId) {
        return bookingService.findByUserId(userId);
    }
    @GetMapping
    public Object getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable String id){
        return userService.getUserById(id);
    }
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable String id ,@RequestBody User user){
        return userService.updateUser(id, user);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }



}
