package CostaneraPMS.controller;

import CostaneraPMS.model.Reserva;
import CostaneraPMS.repository.ReservaRepository;
import CostaneraPMS.repository.HabitacionRepository;
import CostaneraPMS.repository.EstadoHabitacionRepository;

import org.springframework.web.bind.annotation.*;
import CostaneraPMS.model.EstadoHabitacion;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservaController {

    private final ReservaRepository reservaRepository;

    private final HabitacionRepository habitacionRepository;

    private final EstadoHabitacionRepository estadoHabitacionRepository;

    public ReservaController(ReservaRepository reservaRepository,
            HabitacionRepository habitacionRepository,
            EstadoHabitacionRepository estadoHabitacionRepository) {
        this.reservaRepository = reservaRepository;
        this.habitacionRepository = habitacionRepository;
        this.estadoHabitacionRepository = estadoHabitacionRepository;
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

    @PutMapping("/{id}/checkin")
    public Reserva realizarCheckin(
            @PathVariable Long id) {

        Reserva reserva = reservaRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Reserva no encontrada"));

        reserva.setFechaHoraCheckinReal(
                LocalDateTime.now());

        EstadoHabitacion ocupada = estadoHabitacionRepository
                .findByNombre("OCUPADA");

        reserva.getHabitacion()
                .setEstadoHabitacion(ocupada);

        habitacionRepository.save(
                reserva.getHabitacion());

        return reservaRepository.save(
                reserva);
    }

    @PutMapping("/{id}/checkout")
    public Reserva realizarCheckout(
            @PathVariable Long id) {

        Reserva reserva = reservaRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Reserva no encontrada"));

        reserva.setFechaHoraCheckoutReal(
                LocalDateTime.now());

        EstadoHabitacion disponible = estadoHabitacionRepository
                .findByNombre("DISPONIBLE");

        reserva.getHabitacion()
                .setEstadoHabitacion(disponible);

        habitacionRepository.save(
                reserva.getHabitacion());

        return reservaRepository.save(
                reserva);
    }

}