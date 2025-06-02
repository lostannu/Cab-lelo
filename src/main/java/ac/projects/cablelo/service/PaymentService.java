package ac.projects.cablelo.service;

import ac.projects.cablelo.model.Payment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PaymentService {
    ResponseEntity<List<Payment>> getAllPayments();
    ResponseEntity<Payment> getPaymentById(String id);
    ResponseEntity<String> createPayment(Payment payment);
    ResponseEntity<String> updatePayment(String id, Payment payment);
    ResponseEntity<String> deletePayment(String id);
}
