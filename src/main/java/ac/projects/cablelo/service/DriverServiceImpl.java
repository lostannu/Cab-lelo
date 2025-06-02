package ac.projects.cablelo.service;

import ac.projects.cablelo.model.Driver;
import ac.projects.cablelo.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        return ResponseEntity.ok(drivers);
    }

    @Override
    public ResponseEntity<Driver> getDriverById(String id) {
        Optional<Driver> driver = driverRepository.findById(id);
        return driver.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<String> createDriver(Driver driver) {
        driverRepository.save(driver);
        return ResponseEntity.status(HttpStatus.CREATED).body("Driver created successfully");
    }

    @Override
    public ResponseEntity<String> updateDriver(String id, Driver driver) {
        Optional<Driver> existingDriver = driverRepository.findById(id);
        if (existingDriver.isPresent()) {
            Driver updatedDriver = existingDriver.get();
            updatedDriver.setName(driver.getName());
            updatedDriver.setLicenseNumber(driver.getLicenseNumber());
            updatedDriver.setPhoneNumber(driver.getPhoneNumber());
            updatedDriver.setEmail(driver.getEmail());
            updatedDriver.setAvailable(driver.isAvailable());
            driverRepository.save(updatedDriver);
            return ResponseEntity.ok("Driver updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver not found");
        }
    }

    @Override
    public ResponseEntity<String> deleteDriver(String id) {
        if (driverRepository.existsById(id)) {
            driverRepository.deleteById(id);
            return ResponseEntity.ok("Driver deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver not found");
        }
    }
}
