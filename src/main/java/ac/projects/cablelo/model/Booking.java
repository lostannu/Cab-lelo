package ac.projects.cablelo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @NonNull
    private String id;

    @NonNull
    private String userId;
    @NonNull
    private String driverId;
    @NonNull
    private String cabId;
    private String pickUpLocation;
    private String dropLocation;
    private LocalDateTime bookingTime;
    private Double distance;
    private Double fare;
    private String status;



}
