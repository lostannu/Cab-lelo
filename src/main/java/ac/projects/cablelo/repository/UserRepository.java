package ac.projects.cablelo.repository;

import ac.projects.cablelo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);  // already needed for login

    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email); // <-- Add this method
}
