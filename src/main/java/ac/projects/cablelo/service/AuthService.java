package ac.projects.cablelo.service;

import ac.projects.cablelo.model.User;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> login(String username, String password);
    ResponseEntity<String> register(User user);
}
