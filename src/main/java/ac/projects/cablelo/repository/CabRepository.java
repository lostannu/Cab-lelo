package ac.projects.cablelo.repository;

import ac.projects.cablelo.model.Cab;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CabRepository extends MongoRepository<Cab, String> {
    List<Cab> findByAvailable(boolean available);
}
