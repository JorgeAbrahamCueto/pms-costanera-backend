package CostaneraPMS.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estado_habitacion")
public class EstadoHabitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstadoHabitacion;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion;

}