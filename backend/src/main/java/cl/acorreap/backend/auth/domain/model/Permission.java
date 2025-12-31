package cl.acorreap.backend.auth.domain.model;

import cl.acorreap.backend.auth.infrastructure.entity.PermissionEntity;
import cl.acorreap.backend.common.formatter.AuthorityNameFormatter;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Permission {

    private Integer id;

    private String name;

    private LocalDateTime createdAt;

    private boolean enabled;

    private Permission(Integer id, String name, LocalDateTime createdAt, boolean enabled) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.enabled = enabled;
    }

    public static Permission create(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Permission name cannot be null or blank");
        }
        return new Permission(
                null, name,
                LocalDateTime.now(),
                true
        );
    }

    public static Permission rehydrate(Integer id, String name, LocalDateTime createdAt, boolean enabled) {
        return new Permission(id, name, createdAt, enabled);
    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Permission name cannot be null or blank");
        }
        this.name = newName;
    }

    public String getInternalName() {
        return "PRIVILEGE_" + AuthorityNameFormatter.format(name);
    }

    public void disable() {
        this.enabled = false;
    }

    public void enable() {
        this.enabled = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Permission)) return false;
        return id != null && id.equals(((Permission) obj).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
