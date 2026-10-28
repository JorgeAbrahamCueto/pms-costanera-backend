package CostaneraPMS.controller;

import CostaneraPMS.model.Usuario;
import CostaneraPMS.repository.UsuarioRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario) {
        if (usuarioRepository.existsByUsernameusuario(usuario.getUsernameusuario())) {
            return ResponseEntity.badRequest().body("El nombre de usuario ya está en uso.");
        }
        usuario.setEstado("ACTIVO");
        usuario.setIntentosFallidos(0);
        usuario.setFechaCreacion(LocalDateTime.now());
        usuario.setFechaActualizacion(LocalDateTime.now());

        usuarioRepository.save(usuario);

        return ResponseEntity.ok().body("Usuario registrado exitosamente.");
    }
}