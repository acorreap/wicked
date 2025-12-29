package cl.acorreap.backend.auth.infrastructure.mapper;

import cl.acorreap.backend.auth.domain.model.Permission;
import cl.acorreap.backend.auth.infrastructure.entity.PermissionEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PermissionMapper {

    public static PermissionEntity toEntity(Permission model) {
        PermissionEntity entity = new PermissionEntity();

        if (model.getId() != null) {
            entity.setId(model.getId());
        }
        entity.setName(model.getName());
        entity.setCreatedAt(model.getCreatedAt());
        entity.setEnabled(model.isEnabled());
        return entity;
    }

    public static Permission toModel(PermissionEntity entity) {
        return Permission.rehydrate(
                entity.getId(),
                entity.getName(),
                entity.getCreatedAt(),
                entity.isEnabled()
        );
    }
}
