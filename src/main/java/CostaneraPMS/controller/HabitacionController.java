package CostaneraPMS.controller;

import CostaneraPMS.model.Reserva;

import CostaneraPMS.model.Habitacion;
import CostaneraPMS.repository.HabitacionRepository;
import org.springframework.web.bind.annotation.*;
import CostaneraPMS.repository.ReservaRepository;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/habitaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class HabitacionController {

    private final HabitacionRepository habitacionRepository;
    private final ReservaRepository reservaRepository;

    public HabitacionController(
            HabitacionRepository habitacionRepository,
            ReservaRepository reservaRepository) {

        this.habitacionRepository = habitacionRepository;
        this.reservaRepository = reservaRepository;
    }

    @GetMapping
    public List<Habitacion> listarHabitaciones() {
        return habitacionRepository.findAll();
    }

    @GetMapping("/disponibles")
    public List<Habitacion> listarDisponibles(

            @RequestParam LocalDateTime checkin,

            @RequestParam LocalDateTime checkout) {

        List<Habitacion> disponibles = new ArrayList<>();

        List<Habitacion> habitaciones = habitacionRepository.findAll();

        for (Habitacion habitacion : habitaciones) {

            List<Reserva> reservas = reservaRepository
                    .findByHabitacionAndFechaHoraCheckinProgramadoLessThanAndFechaHoraCheckoutProgramadoGreaterThan(
                            habitacion,
                            checkout,
                            checkin);

            if (reservas.isEmpty()) {
                disponibles.add(habitacion);
            }
        }

        return disponibles;
    }

}