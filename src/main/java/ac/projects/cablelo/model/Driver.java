package ac.projects.cablelo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "drivers")
public class Driver {

    @Id
    private String id;

    private String name;
    private String licenseNumber;
    private String phoneNumber;
    private String vehicleNumber;
    private String vehicleType;

    public Driver() {}
    public Driver( String name, String licenseNumber, String phoneNumber, String vehicleNumber, String vehicleType) {

        this.name = name;
        this.licenseNumber = licenseNumber;
        this.phoneNumber = phoneNumber;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;

    }

}
