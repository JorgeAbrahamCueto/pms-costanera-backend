package CostaneraPMS.controller;

import CostaneraPMS.model.Reserva;
import CostaneraPMS.repository.ReservaRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDateTime;

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

    @PostMapping
    public Reserva registrarReserva(
            @RequestBody Reserva reserva) {

        List<Reserva> reservasExistentes = reservaRepository
                .findByHabitacionAndFechaHoraCheckinProgramadoLessThanAndFechaHoraCheckoutProgramadoGreaterThan(
                        reserva.getHabitacion(),
                        reserva.getFechaHoraCheckoutProgramado(),
                        reserva.getFechaHoraCheckinProgramado());

        if (!reservasExistentes.isEmpty()) {
            throw new RuntimeException(
                    "La habitación no está disponible para las fechas seleccionadas");
        }

        reserva.setFechaReserva(
                LocalDateTime.now());

        return reservaRepository.save(
                reserva);
    }

}