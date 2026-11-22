package CostaneraPMS.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estado_reserva")
public class EstadoReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstadoReserva;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

}