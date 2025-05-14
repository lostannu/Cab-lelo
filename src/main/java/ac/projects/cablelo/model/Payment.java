package ac.projects.cablelo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "payments")
public class Payment {

    @Id
    private String paymentId;

    private String bookingId;
    private Double amount;
    private String method; // UPI, Card, Cash, Wallet
    private LocalDateTime paymentTime;
    private String status;

    public Payment() {}
    public Payment( String paymentId, String bookingId, Double amount, String method, LocalDateTime paymentTime, String status) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.method = method;
        this.paymentTime = paymentTime;
        this.status = status;
    }

}
