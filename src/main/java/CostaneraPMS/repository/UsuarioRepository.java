package CostaneraPMS.repository;

import CostaneraPMS.model.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> 
{
    boolean existsByUsernameusuario(String usernameusuario);
    Optional<Usuario> findByDniusuario(String dniusuario);
    List<Usuario> findByNombreusuarioContainingIgnoreCase(String nombre);
    List<Usuario> findByEstado(String estado);
    List<Usuario> findByRol_IdRol(Long idRol);
}
