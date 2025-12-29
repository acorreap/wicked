package cl.acorreap.backend.auth.infrastructure.config;

import cl.acorreap.backend.auth.domain.repository.PermissionRepository;
import cl.acorreap.backend.auth.domain.service.PermissionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfig {

    @Bean
    public PermissionService permissionService(PermissionRepository permissionRepository) {
        return new PermissionService(permissionRepository);
    }
}
