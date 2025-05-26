package ac.projects.cablelo.repository;

import ac.projects.cablelo.model.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DriverRepository extends MongoRepository<Driver, String> {

}
