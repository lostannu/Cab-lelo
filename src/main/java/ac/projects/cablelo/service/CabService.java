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
public class CabService {

    @Autowired
    private CabRepository cabRepository;

    public ResponseEntity<List<Cab>> getAllCabs() {
        List<Cab> ls=cabRepository.findAll();
        if(ls.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(ls, HttpStatus.OK);
        }
    }
    public ResponseEntity<Cab> getCab(String id) {
        Optional<Cab> cabOptional = cabRepository.findById(id);
        if(cabOptional.isPresent()){
            return new ResponseEntity<>(cabOptional.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<String> createCab(Cab cab) {
        cabRepository.save(cab);
        return new ResponseEntity<>("Cab details added with id: "+cab.getId(), HttpStatus.CREATED);
    }
    public ResponseEntity<String> updateCab(String id, Cab updatedCab) {
        Optional<Cab> cabOptional = cabRepository.findById(id);
        if(cabOptional.isPresent()){
            updatedCab.setId(id);
            cabRepository.save(updatedCab);
            return new ResponseEntity<>("Cab details updated with "+updatedCab.getId(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Cab Found ",HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<String> deleteCab(String id) {
        Optional<Cab> cabOptional = cabRepository.findById(id);
        if(cabOptional.isPresent()){
            cabRepository.delete(cabOptional.get());
            return new ResponseEntity<>("Cab deleted ", HttpStatus.OK);

        }else {
            return new ResponseEntity<>("No Cab Found ", HttpStatus.NOT_FOUND);
        }
    }
}
