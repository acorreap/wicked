package cl.acorreap.backend.auth.domain.services;

import cl.acorreap.backend.auth.domain.models.Permission;
import cl.acorreap.backend.auth.domain.models.Role;

public class RoleDomainService {

    public void assignPermissionToRole(Role role, Permission permission) {
        if (!permission.isEnabled()) {
            throw new IllegalStateException("No se puede asignar un permiso deshabilitado al rol.");
        }

        role.addPermission(permission);
    }
}
