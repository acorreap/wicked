package cl.acorreap.backend.auth.domain.services;

import cl.acorreap.backend.auth.domain.models.Role;
import cl.acorreap.backend.auth.domain.models.User;

import java.util.Set;

public class UserDomainService {

    public void assertUserCanBeUpdated(User user) {
        if (!user.isEnabled()) {
            throw new IllegalStateException("No se puede actualizar un usuario deshabilitado.");
        }
    }

    public void assertUserCanLogin(User user) {
        if (!user.isEnabled()) {
            throw new IllegalStateException("El usuario está deshabilitado y no puede iniciar sesión.");
        }
    }

    public void assignRoleToUser(User user, Role role) {
        if (!role.isEnabled()) {
            throw new IllegalStateException("No se puede asignar un rol deshabilitado al usuario.");
        }

        user.addRole(role);
    }

    public void removeRoleFromUser(User user, Role role) {
        user.removeRole(role);
    }

    public void replaceRoles(User user, Set<Role> newRoles) {
        for (Role role : newRoles) {
            if (!role.isEnabled()) {
                throw new IllegalStateException("No se puede asignar un rol deshabilitado al usuario.");
            }
        }

        user.cleanRoles();
        newRoles.forEach(user::addRole);
    }

    public void assertRoleNotAlreadyAssigned(User user, Role role) {
        if (user.getRoles().contains(role)) {
            throw new IllegalStateException("El rol ya está asignado al usuario.");
        }
    }

    public void validateUserCanReceiveRole(User user, Role role) {
        if (!user.isEnabled()) {
            throw new IllegalStateException("No se puede asignar un rol a un usuario deshabilitado.");
        }

        if (!role.isEnabled()) {
            throw new IllegalStateException("No se puede asignar un rol deshabilitado al usuario.");
        }
    }
}
