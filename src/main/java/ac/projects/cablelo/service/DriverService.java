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
public class DriverService {

    private DriverRepository driverRepository;
    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }
    public ResponseEntity<Object> getDriverById(String id) {
        Optional<Driver> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isPresent()) {
            return new ResponseEntity<>(optionalDriver.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Driver Found with id : "+id,HttpStatus.NOT_FOUND);
        }
    }
    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }
    public ResponseEntity<Object> updateDriver(String id,Driver updatedDriver) {
        Optional<Driver> driverOptional = driverRepository.findById(id);
        if(driverOptional.isPresent()){
            updatedDriver.setId(id);
            driverRepository.save(updatedDriver);
            return new ResponseEntity<>("Driver Updated ", HttpStatus.OK);
        }
        return new ResponseEntity<>("No Driver Found with id : "+id,HttpStatus.NOT_FOUND);


    }
    public ResponseEntity<String> deleteDriver(String id) {
        Optional<Driver> driverOptional = driverRepository.findById(id);
        if (driverOptional.isPresent()) {
            driverRepository.delete(driverOptional.get());
            return new ResponseEntity<>("Driver deleted with id: " + id, HttpStatus.OK);

        }
        else{

            return new ResponseEntity<>("No Driver with id: " + id,HttpStatus.NOT_FOUND);
        }
    }
}
