package cl.acorreap.backend.auth.application.mapper;

import cl.acorreap.backend.auth.application.dto.PermissionResponseDto;
import cl.acorreap.backend.auth.domain.model.Permission;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PermissionDtoMapper {

    public static PermissionResponseDto toResponse(Permission permission) {
        PermissionResponseDto response = new PermissionResponseDto();

        response.setId(permission.getId());
        response.setName(permission.getName());
        response.setCreatedAt(permission.getCreatedAt());
        response.setEnabled(permission.isEnabled());

        return response;
    }

    public static Permission toDomain(PermissionResponseDto permissionResponseDto) {

        return Permission.rehydrate(
                permissionResponseDto.getId() != null ? permissionResponseDto.getId() : null,
                permissionResponseDto.getName(),
                permissionResponseDto.getCreatedAt(),
                permissionResponseDto.isEnabled()
        );
    }
}
