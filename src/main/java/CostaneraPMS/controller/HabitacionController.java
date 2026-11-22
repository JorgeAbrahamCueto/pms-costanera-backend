package CostaneraPMS.controller;

import CostaneraPMS.model.Habitacion;
import CostaneraPMS.repository.HabitacionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habitaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class HabitacionController {

    private final HabitacionRepository habitacionRepository;

    public HabitacionController(
            HabitacionRepository habitacionRepository) {

        this.habitacionRepository = habitacionRepository;

    }

    @GetMapping
    public List<Habitacion> listarHabitaciones() {
        return habitacionRepository.findAll();
    }

}