package CostaneraPMS.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @Column(unique = true, nullable = false)
    private String ruc;

    @Column(nullable = false)
    private String razonSocial;

    @Column(nullable = false)
    private String tipoProveedor;

    @Column(nullable = false)
    private String estado;

    @Column(name = "estado_sunat")
    private String estadoSunat;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    // getters y setters
}