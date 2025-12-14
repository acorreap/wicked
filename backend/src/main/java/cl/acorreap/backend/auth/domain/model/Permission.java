package cl.acorreap.backend.auth.domain.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Permission {

    private Integer id;

    private String name;

    private LocalDateTime createdAt;

    private boolean enabled;

}
