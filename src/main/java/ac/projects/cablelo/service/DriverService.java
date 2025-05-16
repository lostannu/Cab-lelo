package ac.projects.cablelo.service;

import ac.projects.cablelo.model.Driver;
import ac.projects.cablelo.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private DriverRepository driverRepository;
    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
    public Driver getDriverById(String id) {
        return driverRepository.findById(id).orElse(null);
    }
    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }
    public Driver updateDriver(String id,Driver updatedDriver) {
        Optional<Driver> driverOptional = driverRepository.findById(id);
        if(driverOptional.isPresent()){
            updatedDriver.setId(id);
            return driverRepository.save(updatedDriver);
        }
        return null;


    }
    public String deleteDriver(String id) {
        driverRepository.deleteById(id);
        return "Driver deleted with id: " + id;
    }
}
