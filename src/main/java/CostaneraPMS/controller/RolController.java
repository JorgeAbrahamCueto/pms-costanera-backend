package CostaneraPMS.controller;

import CostaneraPMS.model.Rol;
import CostaneraPMS.repository.RolRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class RolController {
     private final RolRepository rolRepository;

    public RolController(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }
    @GetMapping("/roles")
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }
}
