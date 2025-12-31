package cl.acorreap.backend.auth.infrastructure.mapper;

import cl.acorreap.backend.auth.domain.model.Role;
import cl.acorreap.backend.auth.infrastructure.entity.RoleEntity;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.stream.Collectors;

@UtilityClass
public class RoleMapper {

    public static RoleEntity toEntity(Role role) {
        RoleEntity roleEntity = new RoleEntity();

        if (role.getId() != null) {
            roleEntity.setId(role.getId());
        }

        roleEntity.setName(role.getName());
        roleEntity.setInternalName(role.getInternalName());
        roleEntity.setCreatedAt(role.getCreatedAt());
        roleEntity.setLastUpdated(role.getLastUpdated());
        roleEntity.setEnabled(role.isEnabled());

        return roleEntity;
    }

    public static Role toModel(RoleEntity roleEntity) {
        return Role.rehydrate(
                roleEntity.getId(),
                roleEntity.getName(),
                roleEntity.getCreatedAt(),
                roleEntity.getLastUpdated(),
                roleEntity.isEnabled(),
                roleEntity.getPermissions()
                        .stream()
                        .map(PermissionMapper::toModel)
                        .collect(Collectors.toSet())
        );
    }
}
