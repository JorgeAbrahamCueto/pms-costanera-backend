package CostaneraPMS.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginResponseDTO {
    private String mensaje;
    private String rol;
    private String token;
    private Long idUsuario;
}
