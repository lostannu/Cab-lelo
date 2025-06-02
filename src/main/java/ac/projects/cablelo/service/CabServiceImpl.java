package ac.projects.cablelo.service;

import ac.projects.cablelo.model.Cab;
import ac.projects.cablelo.repository.CabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CabServiceImpl implements CabService {

    private final CabRepository cabRepository;

    @Autowired
    public CabServiceImpl(CabRepository cabRepository) {
        this.cabRepository = cabRepository;
    }

    @Override
    public ResponseEntity<List<Cab>> getAllCabs() {
        List<Cab> cabs = cabRepository.findAll();
        return ResponseEntity.ok(cabs);
    }

    @Override
    public ResponseEntity<Cab> getCab(String id) {
        Optional<Cab> cab = cabRepository.findById(id);
        return cab.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<String> createCab(Cab cab) {
        cabRepository.save(cab);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cab created successfully");
    }

    @Override
    public ResponseEntity<String> updateCab(String id, Cab cab) {
        Optional<Cab> existingCab = cabRepository.findById(id);
        if (existingCab.isPresent()) {
            Cab updatedCab = existingCab.get();
            updatedCab.setDriverId(cab.getDriverId());
            updatedCab.setLicensePlate(cab.getLicensePlate());
            updatedCab.setModel(cab.getModel());
            updatedCab.setCategory(cab.getCategory());
            updatedCab.setAvailable(cab.isAvailable());
            cabRepository.save(updatedCab);
            return ResponseEntity.ok("Cab updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cab not found");
        }
    }

    @Override
    public ResponseEntity<String> deleteCab(String id) {
        if (cabRepository.existsById(id)) {
            cabRepository.deleteById(id);
            return ResponseEntity.ok("Cab deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cab not found");
        }
    }
}
