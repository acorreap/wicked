package cl.acorreap.backend.auth.application.service;

import cl.acorreap.backend.auth.application.dto.CreatePermissionDto;
import cl.acorreap.backend.auth.application.dto.PermissionResponseDto;
import cl.acorreap.backend.auth.application.dto.UpdatePermissionDto;
import cl.acorreap.backend.auth.application.mapper.PermissionDtoMapper;
import cl.acorreap.backend.auth.domain.model.Permission;
import cl.acorreap.backend.auth.domain.service.PermissionService;
import cl.acorreap.backend.auth.infrastructure.mapper.PermissionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissionApplicationService {

    private final PermissionService permissionService;

    public PermissionApplicationService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    public PermissionResponseDto create(CreatePermissionDto createPermissionDto) {
        Permission permission = permissionService.create(createPermissionDto.getName());

        return PermissionDtoMapper.toResponse(permission);
    }

    public PermissionResponseDto update(Integer id, UpdatePermissionDto dto) {
        Permission permission = permissionService.findById(id);

        Permission updated = permissionService.update(permission, dto.getName(), dto.getEnabled());

        return PermissionDtoMapper.toResponse(updated);
    }
}
