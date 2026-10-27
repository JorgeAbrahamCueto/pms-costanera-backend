package CostaneraPMS.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "rol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "id_rol")
    private Long idRol;

    @Column(name = "nombre")
    private String nombreRol;

    @Column (name = "descripcion")
    private String descripcionRol;

    @Column(name = "estado")
    private Boolean estadoRol;

    // Getters and Setters

}
