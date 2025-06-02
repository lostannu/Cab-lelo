package ac.projects.cablelo.service;

import ac.projects.cablelo.model.Driver;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DriverService {
    ResponseEntity<List<Driver>> getAllDrivers();
    ResponseEntity<Driver> getDriverById(String id);
    ResponseEntity<String> createDriver(Driver driver);
    ResponseEntity<String> updateDriver(String id, Driver driver);
    ResponseEntity<String> deleteDriver(String id);
}
