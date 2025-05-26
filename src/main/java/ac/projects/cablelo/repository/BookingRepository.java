package ac.projects.cablelo.repository;

import ac.projects.cablelo.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking,String> {
    List<Booking> findByDriverId(String driverId);
    List<Booking> findByUserId(String userId);
    List<Booking> findByCabId(String cabId);
}
