package ac.projects.cablelo.service;


import ac.projects.cablelo.model.User;
import ac.projects.cablelo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Object> getUserById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No User Found with id : "+id,HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> updateUser(String id, User updatedUser) {
        Optional<User> existing = userRepository.findById(id);
        if (existing.isPresent()) {
            updatedUser.setId(id);
            userRepository.save(updatedUser);
            return new ResponseEntity<>("User UPDATED", HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found with id : "+id,HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<String> deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new ResponseEntity<>("User deleted with id: " + id, HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found with id: " + id, HttpStatus.NOT_FOUND);
    }

}