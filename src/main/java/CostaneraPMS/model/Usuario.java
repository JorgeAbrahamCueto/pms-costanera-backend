package CostaneraPMS.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

    @Column(name = "nombres")
    private String nombreusuario;

    @Column(name = "apellidos")
    private String apellidousuario;

    @Column(name = "dni")
    private String dniusuario;

    @Column(name = "correo")
    private String correousuario;

    @Column(name = "username")
    private String usernameusuario;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "estado")
    private String estado;

    @Column(name = "intentos_fallidos")
    private Integer intentosFallidos;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}