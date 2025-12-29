package cl.acorreap.backend.auth.infrastructure.repostory;

import cl.acorreap.backend.auth.domain.model.Permission;
import cl.acorreap.backend.auth.infrastructure.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionJpaRepository extends JpaRepository<PermissionEntity,Integer>, JpaSpecificationExecutor<PermissionEntity> {
    boolean existsByName(String name);
}
