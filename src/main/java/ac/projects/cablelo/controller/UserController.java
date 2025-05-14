package ac.projects.cablelo.controller;
import ac.projects.cablelo.model.User;
import ac.projects.cablelo.service.UserService;
import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable String id){
        return userService.getUserById(id);
    }
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id ,@RequestBody User user){
        return userService.updateUser(id,user);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id){
        return userService.deleteUser(id);
    }


}
