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
@Document(collection = "drivers")
public class Driver {

    @Id
    private String id;
    private String name;
    private String licenseNumber;
    private String phoneNumber;
    private String email;
    private boolean available;

}
