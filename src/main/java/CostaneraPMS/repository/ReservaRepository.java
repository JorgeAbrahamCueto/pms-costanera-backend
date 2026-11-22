package CostaneraPMS.repository;

import CostaneraPMS.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

import CostaneraPMS.model.Habitacion;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
        List<Reserva> findByHabitacionAndFechaHoraCheckinProgramadoLessThanAndFechaHoraCheckoutProgramadoGreaterThan(
        Habitacion habitacion,
        LocalDateTime fechaCheckout,
        LocalDateTime fechaCheckin
);
}