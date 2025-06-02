package ac.projects.cablelo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "bookings")
public class Booking {

    @Id
    private String id;

    private String userId;

    private String driverId;

    private String cabId;

    private String pickupLocation;

    private String dropLocation;

    private String bookingDate;

    private String bookingTime;

    private String status;

}
