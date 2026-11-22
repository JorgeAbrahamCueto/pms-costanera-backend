package CostaneraPMS.controller;

import CostaneraPMS.model.Huesped;
import CostaneraPMS.repository.HuespedRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/huespedes")
@CrossOrigin(origins = "http://localhost:4200")
public class HuespedController {

    private final HuespedRepository huespedRepository;

    public HuespedController(
            HuespedRepository huespedRepository) {

        this.huespedRepository = huespedRepository;

    }

    @GetMapping
    public List<Huesped> listarHuespedes() {

        return huespedRepository.findAll();

    }

    @PostMapping
    public Huesped registrarHuesped(
            @RequestBody Huesped huesped) {

        return huespedRepository.save(
                huesped);

    }

}