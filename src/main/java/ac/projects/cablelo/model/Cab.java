package ac.projects.cablelo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cabs")
public class Cab {

    @Id
    private String id;

    private String type;
    private String vehicleNumber;
    private String farePerKm;
    private boolean available;

    public Cab() {}
    public Cab(String type, String vehicleNumber, String farePerKm, boolean available) {

        this.type = type;
        this.vehicleNumber = vehicleNumber;
        this.farePerKm = farePerKm;
        this.available = available;

    }
}
