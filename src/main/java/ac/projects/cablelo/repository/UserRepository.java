package ac.projects.cablelo.repository;

import ac.projects.cablelo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {


}
