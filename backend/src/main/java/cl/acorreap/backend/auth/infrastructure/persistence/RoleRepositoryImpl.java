package cl.acorreap.backend.auth.infrastructure.persistence;

import cl.acorreap.backend.auth.domain.model.Role;
import cl.acorreap.backend.auth.domain.repository.RoleRepository;
import cl.acorreap.backend.auth.infrastructure.entity.PermissionEntity;
import cl.acorreap.backend.auth.infrastructure.entity.RoleEntity;
import cl.acorreap.backend.auth.infrastructure.mapper.RoleMapper;
import cl.acorreap.backend.auth.infrastructure.repostory.PermissionJpaRepository;
import cl.acorreap.backend.auth.infrastructure.repostory.RoleJpaRepository;
import cl.acorreap.backend.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;
    private final PermissionJpaRepository permissionJpaRepository;

    @Override
    public Role findById(Integer id) {
        RoleEntity roleEntity = roleJpaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Role with id" + id + "not found"));

        return RoleMapper.toModel(roleEntity);
    }

    @Override
    public Role findByName(String name) {
        RoleEntity roleEntity = roleJpaRepository.findByName(name);

        if (roleEntity == null) {
            throw new EntityNotFoundException("Role with name" + name + " not found");
        }
        return RoleMapper.toModel(roleEntity);
    }

    @Override
    public Role save(Role role) {
        RoleEntity roleEntity = RoleMapper.toEntity(role);
        settingPermissions(role, roleEntity);
        RoleEntity roleSaved = roleJpaRepository.save(roleEntity);

        return RoleMapper.toModel(roleSaved);
    }

    @Override
    public Role update(Role role) {
        if (role.getId() == null) {
            throw new IllegalArgumentException("Role id cannot be null for update");
        }

        RoleEntity existing = roleJpaRepository.findById(role.getId()).orElseThrow(
                () -> new EntityNotFoundException("Role with id" + role.getId() + " not found")
        );

        existing.setName(role.getName());
        existing.setEnabled(role.isEnabled());
        existing.setLastUpdated(role.getLastUpdated());

        settingPermissions(role, existing);

        RoleEntity roleSaved = roleJpaRepository.save(existing);

        return RoleMapper.toModel(roleSaved);
    }

    @Override
    public boolean existsByName(String name) {
        return roleJpaRepository.existsByName(name);
    }

    private void settingPermissions(Role role, RoleEntity roleEntity) {
        List<Integer> permissionIds = role.getPermissions()
                .stream()
                .map(p -> {
                    if (p.getId() == null) {
                        throw new IllegalStateException("Permission must be persisted before assigning to role");
                    }
                    return p.getId();
                })
                .toList();

        List<PermissionEntity> permissionEntities = permissionJpaRepository.findAllById(permissionIds);

        if (permissionEntities.size() != permissionIds.size()) {
            throw new EntityNotFoundException("One or more permissions not found");
        }
        roleEntity.getPermissions().clear();
        roleEntity.getPermissions().addAll(permissionEntities);
    }
}
