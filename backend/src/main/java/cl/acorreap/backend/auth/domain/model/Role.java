package cl.acorreap.backend.auth.domain.model;

import cl.acorreap.backend.common.formatter.AuthorityNameFormatter;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
public class Role {

    private Integer id;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdated;

    private boolean enabled;

    private Set<Permission> permissions;

    private Role(Integer id, String name, LocalDateTime createdAt, LocalDateTime lastUpdated, boolean enabled, Set<Permission> permissions) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
        this.enabled = enabled;

        this.permissions = new HashSet<>(permissions);
    }

    public static Role create(String name, Set<Permission> permissions) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }

        return new Role(
                null,
                name,
                LocalDateTime.now(),
                LocalDateTime.now(),
                true,
                permissions);
    }

    public static Role rehydrate(Integer id, String name, LocalDateTime createdAt, LocalDateTime lastUpdated, boolean enabled, Set<Permission> permissions) {
        return new Role(id, name, createdAt, lastUpdated, enabled, permissions);
    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Role name cannot be null or blank");
        }
        this.name = newName;
        touch();
    }

    public String getInternalName() {
        return "ROLE_" + AuthorityNameFormatter.format(name);
    }

    public boolean hasPermission(String permissionInternalName) {
        return permissions.stream()
                .anyMatch(p -> p.getInternalName().equals(permissionInternalName));
    }

    public void enable() {
        this.enabled = true;
        touch();
    }

    public void disable() {
        this.enabled = false;
        touch();
    }

    private void touch() {
        this.lastUpdated = LocalDateTime.now();
    }

    public void addPermission(Permission permission) {
        if (!hasPermission(permission.getInternalName())) {
            this.permissions.add(permission);
            touch();
        }
    }

    public Set<Permission> getPermissions() {
        return Set.copyOf(this.permissions);
    }

    public void removePermission(Permission permission) {
        if (hasPermission(permission.getInternalName()) && permissions.size() == 1) {
            throw new IllegalArgumentException("Role must have a least one permission");
        }
        if (hasPermission(permission.getInternalName())) {
            this.permissions.remove(permission);
            touch();
        }
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Role)) return false;
        return id != null && id.equals(((Role)obj).id);
    }
}
