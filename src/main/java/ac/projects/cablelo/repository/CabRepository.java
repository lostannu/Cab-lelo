package ac.projects.cablelo.repository;

import ac.projects.cablelo.model.Cab;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CabRepository extends MongoRepository<Cab, String> {
}
