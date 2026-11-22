package CostaneraPMS.controller;

import CostaneraPMS.model.Reserva;
import CostaneraPMS.repository.ReservaRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservaController {

    private final ReservaRepository reservaRepository;

    public ReservaController(
            ReservaRepository reservaRepository) {

        this.reservaRepository = reservaRepository;

    }

    @GetMapping
    public List<Reserva> listarReservas() {

        return reservaRepository.findAll();

    }

}