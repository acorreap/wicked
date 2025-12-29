package cl.acorreap.backend.auth.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePermissionDto {

    @NotBlank(message = "Permission name cannot by empty")
    private String name;


}
