package cl.acorreap.backend.auth.infrastructure.persistence;

import cl.acorreap.backend.auth.domain.model.Permission;
import cl.acorreap.backend.auth.domain.repository.PermissionRepository;
import cl.acorreap.backend.auth.infrastructure.entity.PermissionEntity;
import cl.acorreap.backend.auth.infrastructure.mapper.PermissionMapper;
import cl.acorreap.backend.auth.infrastructure.repostory.PermissionJpaRepository;
import cl.acorreap.backend.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersistenceRepositoryImpl implements PermissionRepository {

    private final PermissionJpaRepository permissionJpaRepository;

    @Override
    public Permission findById(Integer id) {
        PermissionEntity permissionEntity = permissionJpaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Permission with id " + id + " not found"));

        return PermissionMapper.toModel(permissionEntity);
    }

    @Override
    public Permission save(Permission permission) {
        PermissionEntity permissionEntity = PermissionMapper.toEntity(permission);
        permissionEntity.setId(null);
        PermissionEntity saved = permissionJpaRepository.save(permissionEntity);

        return PermissionMapper.toModel(saved);
    }

    @Override
    public boolean existsByName(String name) {
        return permissionJpaRepository.existsByName(name);
    }

    @Override
    public Permission update(Permission permission) {
        PermissionEntity permissionEntity = PermissionMapper.toEntity(permission);
        PermissionEntity saved = permissionJpaRepository.save(permissionEntity);
        return PermissionMapper.toModel(saved);
    }
}
