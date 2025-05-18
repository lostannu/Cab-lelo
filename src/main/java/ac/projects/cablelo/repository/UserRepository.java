package ac.projects.cablelo.repository;

import ac.projects.cablelo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {


    List<User> id(String id);
}
