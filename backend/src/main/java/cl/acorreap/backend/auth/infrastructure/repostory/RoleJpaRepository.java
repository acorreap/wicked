package cl.acorreap.backend.auth.infrastructure.repostory;

import cl.acorreap.backend.auth.infrastructure.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity, Integer>, JpaSpecificationExecutor<RoleEntity> {
    RoleEntity findByName(String name);

    boolean existsByName(String name);
}