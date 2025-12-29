package cl.acorreap.backend.auth.application.dto;

import lombok.Data;


@Data
public class UpdatePermissionDto {

    private String name;


    private Boolean enabled;
}
