package CostaneraPMS.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "habitacion")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHabitacion;

    @ManyToOne
    @JoinColumn(
        name = "id_tipo_habitacion",
        nullable = false
    )
    private TipoHabitacion tipoHabitacion;

    @ManyToOne
    @JoinColumn(
        name = "id_estado_habitacion",
        nullable = false
    )
    private EstadoHabitacion estadoHabitacion;

    @Column(
        nullable = false,
        unique = true
    )
    private String numero;

    @Column(nullable = false)
    private Integer piso;

    private String descripcion;

    private Boolean activa;
}