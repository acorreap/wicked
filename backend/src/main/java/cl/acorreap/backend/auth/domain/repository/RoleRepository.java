package cl.acorreap.backend.auth.domain.repository;

import cl.acorreap.backend.auth.domain.model.Role;

public interface RoleRepository {

    Role findById(Integer id);
    Role findByName(String name);
    Role save(Role role);
    Role update(Role role);

    boolean existsByName(String name);
}
