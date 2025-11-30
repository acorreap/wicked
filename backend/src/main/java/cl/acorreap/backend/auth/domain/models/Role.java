package cl.acorreap.backend.auth.domain.models;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
public class Role {

    private final Integer id;

    private String name;
    private String description;
    private String internalName;

    private boolean enabled;
    private boolean visible;

    private final LocalDateTime createdAt;

    private final Set<Permission> permissions;

    public Role(Integer id,
                String name,
                String description,
                String internalName,
                boolean enabled,
                boolean visible,
                LocalDateTime createdAt,
                Set<Permission> permissions) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.internalName = internalName;
        this.enabled = enabled;
        this.visible = visible;
        this.createdAt = createdAt;

        this.permissions = (permissions != null)
                ? new HashSet<>(permissions)
                : new HashSet<>();
    }

    public static Role createNewRole(String name,
                                     String description,
                                     String internalName) {

        return new Role(
                null,
                name,
                description,
                internalName.toUpperCase(),
                true,
                true,
                LocalDateTime.now(),
                new HashSet<>()
        );
    }

    public void changeName(String newName) {
        this.name = newName;
    }

    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    public void changeInternalName(String newInternalName) {
        this.internalName = newInternalName.toUpperCase();
    }

    public void activate() {
        this.enabled = true;
    }

    public void deactivate() {
        this.enabled = false;
    }

    public void show() {
        this.visible = true;
    }

    public void hide() {
        this.visible = false;
    }

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
    }

    public void removePermission(Permission permission) {
        this.permissions.remove(permission);
    }

    public void cleanPermissions() {
        this.permissions.clear();
    }
}
