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
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/{id}")
    public Optional<Usuario> obtenerUsuario(@PathVariable Long id) {
        return usuarioRepository.findById(id);
    }

    @GetMapping("/dni/{dni}")
    public Optional<Usuario> buscarPorDni(
            @PathVariable String dni) {

        return usuarioRepository.findByDniusuario(dni);
    }

    @GetMapping("/buscar/{nombre}")
    public List<Usuario> buscarPorNombre(
            @PathVariable String nombre) {

        return usuarioRepository
                .findByNombreusuarioContainingIgnoreCase(nombre);
    }

    @GetMapping("/estado/{estado}")
    public List<Usuario> filtrarPorEstado(
            @PathVariable String estado) {

        return usuarioRepository.findByEstado(estado);
    }

    

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(
            @PathVariable Long id,
            @RequestBody Usuario usuarioActualizado) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow();

        if (!usuario.getUsernameusuario().equals(
                usuarioActualizado.getUsernameusuario())
                &&
                usuarioRepository.existsByUsernameusuario(
                        usuarioActualizado.getUsernameusuario())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre de usuario ya está en uso");
        }

        usuario.setNombreusuario(
                usuarioActualizado.getNombreusuario());

        usuario.setApellidousuario(
                usuarioActualizado.getApellidousuario());

        usuario.setDniusuario(
                usuarioActualizado.getDniusuario());

        usuario.setCorreousuario(
                usuarioActualizado.getCorreousuario());

        usuario.setUsernameusuario(
                usuarioActualizado.getUsernameusuario());

        usuario.setPasswordHash(
                usuarioActualizado.getPasswordHash());

        usuario.setRol(
                usuarioActualizado.getRol());

        usuario.setFechaActualizacion(
                LocalDateTime.now());

        return usuarioRepository.save(usuario);
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

    @PatchMapping("/{id}/desactivar")
    public Usuario desactivarUsuario(@PathVariable Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow();

        usuario.setEstado("INACTIVO");
        usuario.setFechaActualizacion(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }

    @PatchMapping("/{id}/activar")
    public Usuario activarUsuario(@PathVariable Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow();

        usuario.setEstado("ACTIVO");
        usuario.setFechaActualizacion(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }

}