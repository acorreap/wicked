package cl.acorreap.backend.auth.domain.repository;

import cl.acorreap.backend.auth.domain.model.Permission;

public interface PermissionRepository {

    Permission findById(Integer id);

    Permission save(Permission permission);

    Permission update(Permission permission);

    boolean existsByName(String name);

}
