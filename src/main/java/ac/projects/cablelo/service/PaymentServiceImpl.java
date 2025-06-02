package ac.projects.cablelo.service;

import ac.projects.cablelo.model.Payment;
import ac.projects.cablelo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return ResponseEntity.ok(payments);
    }

    @Override
    public ResponseEntity<Payment> getPaymentById(String id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<String> createPayment(Payment payment) {
        paymentRepository.save(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body("Payment created successfully");
    }

    @Override
    public ResponseEntity<String> updatePayment(String id, Payment payment) {
        Optional<Payment> existingPayment = paymentRepository.findById(id);
        if (existingPayment.isPresent()) {
            Payment updatedPayment = existingPayment.get();
            updatedPayment.setBookingId(payment.getBookingId());
            updatedPayment.setAmount(payment.getAmount());
            updatedPayment.setPaymentDate(payment.getPaymentDate());
            updatedPayment.setPaymentMethod(payment.getPaymentMethod());
            updatedPayment.setStatus(payment.getStatus());
            paymentRepository.save(updatedPayment);
            return ResponseEntity.ok("Payment updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found");
        }
    }

    @Override
    public ResponseEntity<String> deletePayment(String id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return ResponseEntity.ok("Payment deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found");
        }
    }
}
