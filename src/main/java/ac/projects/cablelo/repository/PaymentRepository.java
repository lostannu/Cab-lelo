package ac.projects.cablelo.repository;

import ac.projects.cablelo.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    Payment findByBookingId(String bookingId);
}
