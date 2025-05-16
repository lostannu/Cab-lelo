package ac.projects.cablelo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cabs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cab {

    @Id
    private String id;

    private String type;
    private String vehicleNumber;
    private String farePerKm;
    private boolean available;

}
