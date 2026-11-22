package CostaneraPMS.repository;

import CostaneraPMS.model.EstadoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoHabitacionRepository
        extends JpaRepository<EstadoHabitacion, Long> {

    EstadoHabitacion findByNombre(String nombre);

}