package ac.projects.cablelo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "cabs")
public class Cab {

    @Id
    private String id;
    private String driverId;
    private String licensePlate;
    private String model;
    private String category; // e.g., Sedan, SUV
    private boolean available;

    // Constructors, Getters, and Setters
}
