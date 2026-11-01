package CostaneraPMS.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FactilizaResponseDTO {

    private Integer status;

    private String message;

    private Boolean success;

    private FactilizaDataDTO data;
}
