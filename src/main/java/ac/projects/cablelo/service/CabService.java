package ac.projects.cablelo.service;

import ac.projects.cablelo.model.Cab;
import ac.projects.cablelo.repository.CabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CabService {

    @Autowired
    private CabRepository cabRepository;

    public Object getAllCabs() {
        List<Cab> ls=cabRepository.findAll();
        if(ls.size()>0) {
            return ls;
        }else{
            return "No Cabs Found";
        }
    }
    public Object getCab(String id) {
        Optional<Cab> cabOptional = cabRepository.findById(id);
        if(cabOptional.isPresent()){
            return cabOptional.get();
        }else {
            return "No Cab found";
        }
    }
    public Cab createCab(Cab cab) {
        return cabRepository.save(cab);
    }
    public Cab updateCab(String id, Cab updatedCab) {
        Optional<Cab> cabOptional = cabRepository.findById(id);
        if(cabOptional.isPresent()){
            updatedCab.setId(id);
            return cabRepository.save(updatedCab);
        }else{
            return null;
        }
    }
    public void deleteCab(String id) {
        Optional<Cab> cabOptional = cabRepository.findById(id);
        if(cabOptional.isPresent()){
            cabRepository.delete(cabOptional.get());

        }else{
            System.out.println("Cab not found");
        }
    }

}
