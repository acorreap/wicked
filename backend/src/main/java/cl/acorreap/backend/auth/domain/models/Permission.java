package cl.acorreap.backend.auth.domain.models;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Permission {

    private final Integer id;

    private final String name;
    private final String description;

    private boolean enabled;
    private boolean visible;

    private final LocalDateTime createdAt;

    public Permission(Integer id,
                      String name,
                      String description,
                      boolean enabled,
                      boolean visible,
                      LocalDateTime createdAt) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.visible = visible;
        this.createdAt = createdAt;
    }

    public static Permission createNewPermission(String name, String description) {

        return new Permission(
                null,
                name.toUpperCase(),
                description,
                true,
                true,
                LocalDateTime.now()
        );
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
}