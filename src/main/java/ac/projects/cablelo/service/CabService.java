package ac.projects.cablelo.service;

import ac.projects.cablelo.model.Cab;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CabService {
    ResponseEntity<List<Cab>> getAllCabs();
    ResponseEntity<Cab> getCab(String id);
    ResponseEntity<String> createCab(Cab cab);
    ResponseEntity<String> updateCab(String id, Cab cab);
    ResponseEntity<String> deleteCab(String id);
}
