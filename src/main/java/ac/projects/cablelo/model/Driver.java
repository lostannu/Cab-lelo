package ac.projects.cablelo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    @Id
    private String id;

    private String name;
    private String licenseNumber;
    private String phoneNumber;
    private String vehicleNumber;
    private String vehicleType;


}
