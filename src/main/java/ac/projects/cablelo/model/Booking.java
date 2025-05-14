package ac.projects.cablelo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "bookings")
public class Booking {

    @Id
    private String id;

    private String userId;
    private String driverId;
    private String cabId;
    private String pickupLocation;
    private String dropLocation;
    private LocalDateTime bookingTime;
    private Double distance;
    private Double fare;
    private String status;

    public Booking() {}

    public Booking(String userId, String driverId, String cabId, String pickupLocation, String dropLocation, LocalDateTime bookingTime, Double distance, Double fare, String status) {
        this.userId = userId;
        this.driverId = driverId;
        this.cabId = cabId;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.bookingTime = bookingTime;
        this.distance = distance;
        this.fare = fare;
        this.status = status;

    }


}
