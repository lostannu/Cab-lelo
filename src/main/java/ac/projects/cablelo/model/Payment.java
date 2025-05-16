package ac.projects.cablelo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    private String paymentId;

    private String bookingId;
    private Double amount;
    private String method; // UPI, Card, Cash, Wallet
    private LocalDateTime paymentTime;
    private String status;

}
