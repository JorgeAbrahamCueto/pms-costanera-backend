package CostaneraPMS.controller;

import CostaneraPMS.dto.LoginRequestDTO;
import CostaneraPMS.dto.LoginResponseDTO;
import CostaneraPMS.model.Usuario;
import CostaneraPMS.repository.UsuarioRepository;
import CostaneraPMS.security.JwtUtil;

import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")

public class AuthController {
        private final UsuarioRepository usuarioRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtUtil jwtUtil;

        public AuthController(
                        UsuarioRepository usuarioRepository,
                        PasswordEncoder passwordEncoder,
                        JwtUtil jwtUtil) {

                this.usuarioRepository = usuarioRepository;
                this.passwordEncoder = passwordEncoder;
                this.jwtUtil = jwtUtil;

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
                if (usuario.get()
                                .getEstado()
                                .equals("BLOQUEADO")) {

                        response.setMensaje(
                                        "Usuario bloqueado");

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
                        return response;
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

                String token = jwtUtil.generarToken(
                                usuario.get().getUsernameusuario(),
                                usuario.get().getRol().getNombreRol());

                response.setToken(token);
                return response;
        }
}
