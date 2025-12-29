package cl.acorreap.backend.auth.infrastructure.controller;

import cl.acorreap.backend.auth.application.dto.CreatePermissionDto;
import cl.acorreap.backend.auth.application.dto.PermissionResponseDto;
import cl.acorreap.backend.auth.application.dto.UpdatePermissionDto;
import cl.acorreap.backend.auth.application.service.PermissionApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionApplicationService permissionApplicationService;

    @PostMapping
    public ResponseEntity<PermissionResponseDto> create(@Valid @RequestBody CreatePermissionDto createPermissionDto) {
        return ResponseEntity.ok(permissionApplicationService.create(createPermissionDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PermissionResponseDto> disabledOrEnable(@PathVariable Integer id, @Valid @RequestBody UpdatePermissionDto update) {
        return ResponseEntity.ok(permissionApplicationService.update(id, update));
    }

}
