package CostaneraPMS.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;

    @ManyToOne
    @JoinColumn(name = "id_habitacion")
    private Habitacion habitacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_estado_reserva")
    private EstadoReserva estadoReserva;

    @ManyToOne
    @JoinColumn(name = "id_canal")
    private CanalReserva canalReserva;

    @Column(name = "fecha_reserva")
    private LocalDateTime fechaReserva;

    @Column(name = "fecha_hora_checkin_programado")
    private LocalDateTime fechaHoraCheckinProgramado;

    @Column(name = "fecha_hora_checkout_programado")
    private LocalDateTime fechaHoraCheckoutProgramado;

    @Column(name = "fecha_hora_checkin_real")
    private LocalDateTime fechaHoraCheckinReal;

    @Column(name = "fecha_hora_checkout_real")
    private LocalDateTime fechaHoraCheckoutReal;

    @Column(name = "cantidad_huespedes")
    private Integer cantidadHuespedes;

    @Column(name = "tarifa_noche")
    private BigDecimal tarifaNoche;

    private String observaciones;
}
