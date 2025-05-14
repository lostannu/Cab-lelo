package ac.projects.cablelo.service;


import ac.projects.cablelo.model.User;
import ac.projects.cablelo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(String id, User updatedUser) {
        Optional<User> existing = userRepository.findById(id);
        if (existing.isPresent()) {
            updatedUser.setId(id);
            return userRepository.save(updatedUser);
        }
        return null;
    }

    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "User deleted with id: " + id;
    }
}