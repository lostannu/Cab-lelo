package ac.projects.cablelo.controller;

import ac.projects.cablelo.model.Cab;
import ac.projects.cablelo.service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabs")
public class CabController {

    private CabService cabService;

    @Autowired
    public CabController(CabService cabService) {
        this.cabService = cabService;
    }

    @GetMapping
    public Object getAllCabs() {
        return cabService.getAllCabs();
    }

    @GetMapping("/{id}")
    public Object getCab(@PathVariable String id) {
        return cabService.getCab(id);
    }

    @PostMapping
    public Cab createCab(@RequestBody Cab cab) {
        return cabService.createCab(cab);
    }

    @PutMapping("/{id}")
    public Cab updateCab(@PathVariable String id, @RequestBody Cab cab) {
        return cabService.updateCab(id, cab);
    }

    @DeleteMapping("/{id}")
    public void deleteCab(@PathVariable String id) {
        cabService.deleteCab(id);
    }
}
