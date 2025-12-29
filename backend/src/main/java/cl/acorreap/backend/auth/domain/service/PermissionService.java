package cl.acorreap.backend.auth.domain.service;

import cl.acorreap.backend.auth.domain.exception.PermissionAlreadyException;
import cl.acorreap.backend.auth.domain.model.Permission;
import cl.acorreap.backend.auth.domain.repository.PermissionRepository;
import cl.acorreap.backend.common.formatter.AuthorityNameFormatter;

public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission findById(Integer id) {
        return permissionRepository.findById(id);
    }

    public Permission create(String name) {


        String formattedName = AuthorityNameFormatter.format(name);

        if (permissionRepository.existsByName(formattedName)) {
            throw new PermissionAlreadyException("Permission with name " + name + " already exists");
        }

        return permissionRepository.save(Permission.create(formattedName));
    }

    public Permission update(Permission permission, String name, Boolean enabled) {

        if (name != null) {
            String formattedName = AuthorityNameFormatter.format(name);

            if (!permission.getName().equals(formattedName)
                    && permissionRepository.existsByName(formattedName)) {
                throw new PermissionAlreadyException(
                        "Permission with name " + formattedName + " already exists"
                );
            }
            permission.rename(formattedName);
        }

        if (enabled != null) {
            if (enabled) {
                permission.enable();
            } else {
                permission.disable();
            }
        }

        return permissionRepository.update(permission);
    }

}
