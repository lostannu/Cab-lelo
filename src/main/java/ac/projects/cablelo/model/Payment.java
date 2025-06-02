package ac.projects.cablelo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "payments")
public class Payment {

    @Id
    private String id;

    private String bookingId;

    private double amount;

    private String paymentMethod;

    private String paymentStatus; // This already exists

    private String paymentDate;

    private String status; // <-- Add this only if needed separately
}
