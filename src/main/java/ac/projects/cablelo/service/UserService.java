package ac.projects.cablelo.service;



import ac.projects.cablelo.model.User;
import ac.projects.cablelo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> createUser(User user) {
        userRepository.save(user);
        return new ResponseEntity<>("User Created ", HttpStatus.OK);
    }

    public ResponseEntity<List<User>> getAllUsers() {

        List<User> ls=userRepository.findAll();
        if(ls.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(ls, HttpStatus.OK);
        }
    }

    public ResponseEntity<User> getUserById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> updateUser(String id, User updatedUser) {
        Optional<User> existing = userRepository.findById(id);
        if (existing.isPresent()) {
            updatedUser.setId(id);
            userRepository.save(updatedUser);
            return new ResponseEntity<>("User UPDATED", HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found with id : "+id,HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<String> deleteUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return new ResponseEntity<>("User Deleted ", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found with id: " + id, HttpStatus.NOT_FOUND);
    }

}