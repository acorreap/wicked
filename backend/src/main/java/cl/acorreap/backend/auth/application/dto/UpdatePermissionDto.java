package cl.acorreap.backend.auth.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class UpdatePermissionDto {

    private String name;


    private Boolean enabled;
}
