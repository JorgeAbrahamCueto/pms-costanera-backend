package CostaneraPMS.repository;

import CostaneraPMS.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProveedorRepository
        extends JpaRepository<Proveedor, Long> {

    boolean existsByRuc(String ruc);

    Optional<Proveedor> findByRuc(String ruc);

    List<Proveedor> findByRazonSocialContainingIgnoreCase(
        String razonSocial);

}