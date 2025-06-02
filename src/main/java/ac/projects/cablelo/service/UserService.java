package ac.projects.cablelo.service;

import ac.projects.cablelo.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<List<User>> getAllUsers();
    ResponseEntity<User> getUserById(String id);
    ResponseEntity<String> createUser(User user);
    ResponseEntity<String> updateUser(String id, User user);
    ResponseEntity<String> deleteUser(String id);
}
