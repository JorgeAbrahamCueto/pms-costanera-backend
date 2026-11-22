package CostaneraPMS.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reserva_huesped")
public class ReservaHuesped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva_huesped")
    private Long idReservaHuesped;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "id_huesped")
    private Huesped huesped;

    @Column(name = "es_titular")
    private Boolean esTitular;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
}