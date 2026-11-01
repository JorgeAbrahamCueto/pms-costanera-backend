package CostaneraPMS.controller;

import CostaneraPMS.model.Proveedor;
import CostaneraPMS.repository.ProveedorRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import CostaneraPMS.dto.SunatProveedorDTO;
import CostaneraPMS.dto.FactilizaResponseDTO;

@RestController
@RequestMapping("/proveedores")
@CrossOrigin(origins = "http://localhost:4200")
public class ProveedorController {

    private final ProveedorRepository proveedorRepository;

    public ProveedorController(
            ProveedorRepository proveedorRepository) {

        this.proveedorRepository = proveedorRepository;
    }

    @GetMapping
    public List<Proveedor> listarProveedores() {

        return proveedorRepository.findAll();
    }

    @GetMapping("/sunat/{ruc}")
    public SunatProveedorDTO consultarSunat(
            @PathVariable String ruc) {

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI0MjczMCJ9.PGKhltQltSSrQEBjEsJVBK8IsLBuBPkhpYf7LhwMgvg";
        String url = "https://api.factiliza.com/v1/ruc/info/" + ruc;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<FactilizaResponseDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                FactilizaResponseDTO.class);

        FactilizaResponseDTO factiliza = response.getBody();
        SunatProveedorDTO dto = new SunatProveedorDTO();

        dto.setRuc(
                factiliza.getData().getRuc());

        dto.setRazonSocial(
                factiliza.getData().getRazonSocial());

        dto.setEstadoSunat(
                factiliza.getData().getEstadoSunat());

        return dto;
    }

    @GetMapping("/ruc/{ruc}")
    public Optional<Proveedor> obtenerProveedorPorRuc(
            @PathVariable String ruc) {

        return proveedorRepository.findByRuc(ruc);
    }

    @GetMapping("/{id}")
    public Optional<Proveedor> obtenerProveedor(
            @PathVariable Long id) {

        return proveedorRepository.findById(id);
    }

    @GetMapping("/buscar/{razonSocial}")
    public List<Proveedor> buscarPorRazonSocial(
            @PathVariable String razonSocial) {

        return proveedorRepository
                .findByRazonSocialContainingIgnoreCase(
                        razonSocial);
    }

    @PostMapping
    public ResponseEntity<String> registrarProveedor(
            @RequestBody Proveedor proveedor) {

        if (proveedorRepository.existsByRuc(
                proveedor.getRuc())) {

            return ResponseEntity.badRequest()
                    .body("El proveedor ya existe.");
        }

        if (!"ACTIVO".equals(
                proveedor.getEstadoSunat())) {

            return ResponseEntity.badRequest()
                    .body("Proveedor no habilitado en SUNAT.");
        }

        proveedor.setEstado("ACTIVO");

        proveedor.setFechaCreacion(
                LocalDateTime.now());

        proveedor.setFechaActualizacion(
                LocalDateTime.now());

        proveedorRepository.save(proveedor);

        return ResponseEntity.ok(
                "Proveedor registrado exitosamente.");
    }

    @PatchMapping("/{id}/desactivar")
    public Proveedor desactivarProveedor(@PathVariable Long id) {

        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow();

        proveedor.setEstado("INACTIVO");
        proveedor.setFechaActualizacion(LocalDateTime.now());

        return proveedorRepository.save(proveedor);
    }

    @PatchMapping("/{id}/activar")
    public Proveedor activarProveedor(@PathVariable Long id) {

        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow();

        proveedor.setEstado("ACTIVO");
        proveedor.setFechaActualizacion(LocalDateTime.now());

        return proveedorRepository.save(proveedor);
    }

}