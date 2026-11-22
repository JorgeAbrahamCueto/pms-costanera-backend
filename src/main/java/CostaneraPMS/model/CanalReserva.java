package CostaneraPMS.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "canal_reserva")
public class CanalReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCanal;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    private Boolean activo;

}