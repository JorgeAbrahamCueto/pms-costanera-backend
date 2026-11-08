package CostaneraPMS.controller;

import CostaneraPMS.dto.LoginRequestDTO;
import CostaneraPMS.dto.LoginResponseDTO;
import CostaneraPMS.model.Usuario;
import CostaneraPMS.repository.UsuarioRepository;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")

public class AuthController {
    private final UsuarioRepository usuarioRepository;

    public AuthController(
            UsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;

    }

    @PostMapping("/login")
    public LoginResponseDTO login(
            @RequestBody LoginRequestDTO request) {
        Optional<Usuario> usuario = usuarioRepository
                .findByUsernameusuario(
                        request.getUsername());

        LoginResponseDTO response = new LoginResponseDTO();

        response.setMensaje(
                "Login recibido correctamente");

        if (usuario.isPresent()) {

            response.setRol(
                    usuario.get()
                            .getRol()
                            .getNombreRol());

        } else {

            response.setRol(
                    "NO ENCONTRADO");

        }

        return response;
    }

}
