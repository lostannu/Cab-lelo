package ac.projects.cablelo.controller;

import ac.projects.cablelo.model.Driver;
import ac.projects.cablelo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping
    public List<Driver> getAllUsers(){
        return driverService.getAllDrivers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getDriverById(@PathVariable String id) {
        return driverService.getDriverById(id);
    }

    @PostMapping
    public Driver createDriver(@RequestBody Driver driver) {
        return driverService.createDriver(driver);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDriver(@PathVariable String id, @RequestBody Driver driver) {
        return driverService.updateDriver(id, driver);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable String id) {
        return driverService.deleteDriver(id);
    }
}


