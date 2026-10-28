package CostaneraPMS.controller;

import CostaneraPMS.model.Usuario;
import CostaneraPMS.repository.UsuarioRepository;
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
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {

        usuario.setEstado("ACTIVO");
        usuario.setIntentosFallidos(0);
        usuario.setFechaCreacion(LocalDateTime.now());
        usuario.setFechaActualizacion(LocalDateTime.now());

        usuarioRepository.save(usuario);

        return usuario;
    }
}