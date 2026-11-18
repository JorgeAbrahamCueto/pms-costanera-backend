package CostaneraPMS.controller;

import CostaneraPMS.dto.LoginRequestDTO;
import CostaneraPMS.dto.LoginResponseDTO;
import CostaneraPMS.model.Usuario;
import CostaneraPMS.repository.UsuarioRepository;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")

public class AuthController {
        private final UsuarioRepository usuarioRepository;
        private final PasswordEncoder passwordEncoder;

        public AuthController(
                        UsuarioRepository usuarioRepository,
                        PasswordEncoder passwordEncoder) {

                this.usuarioRepository = usuarioRepository;
                this.passwordEncoder = passwordEncoder;

        }

        @PostMapping("/login")
        public LoginResponseDTO login(
                        @RequestBody LoginRequestDTO request) {
                Optional<Usuario> usuario = usuarioRepository
                                .findByUsernameusuario(
                                                request.getUsername());
                LoginResponseDTO response = new LoginResponseDTO();
                if (!usuario.isPresent()) {
                        response.setMensaje(
                                        "Usuario y/o Contraseña Inválidos");
                        return response;
                }
                if (!passwordEncoder.matches(
                                request.getPassword(),
                                usuario.get().getPasswordHash())) {

                        usuario.get().setIntentosFallidos(
                                        usuario.get().getIntentosFallidos() + 1);

                        if (usuario.get().getIntentosFallidos() >= 3) {

                                usuario.get().setEstado(
                                                "BLOQUEADO");

                        }

                        usuarioRepository.save(
                                        usuario.get());

                        response.setMensaje(
                                        "Usuario y/o Contraseña Inválidos");
                }
                usuario.get().setIntentosFallidos(0);

                usuarioRepository.save(
                                usuario.get());
                response.setMensaje(
                                "Login recibido correctamente");
                response.setRol(
                                usuario.get()
                                                .getRol()
                                                .getNombreRol());
                return response;
        }
}
