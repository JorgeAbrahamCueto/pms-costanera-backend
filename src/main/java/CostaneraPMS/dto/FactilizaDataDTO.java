package CostaneraPMS.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FactilizaDataDTO {

    @JsonProperty("numero")
    private String ruc;

    @JsonProperty("nombre_o_razon_social")
    private String razonSocial;

    @JsonProperty("estado")
    private String estadoSunat;
}