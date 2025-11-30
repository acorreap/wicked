package cl.acorreap.backend.auth.domain.models;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
public class User {

    private final Integer id;

    private String username;

    private String password;

    private String email;

    private String names;

    private String firstSurname;

    private String secondSurname;

    private final LocalDateTime registerDate;

    private LocalDateTime lastPasswordChange;

    private boolean enabled;

    private boolean visible;

    private final Set<Role> roles;

    public User(Integer id, String username, String password, String email, String names, String firstSurname, String secondSurname, LocalDateTime registerDate, LocalDateTime lastPasswordChange, boolean enabled, boolean visible, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.names = names;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.registerDate = registerDate;
        this.lastPasswordChange = lastPasswordChange;
        this.enabled = enabled;
        this.visible = visible;

        this.roles = roles != null
                ? new HashSet<>(roles)
                : new HashSet<>();
    }

    public static User createNewUser(String username,
                                     String rawPassword,
                                     String email,
                                     String names,
                                     String firstSurname,
                                     String secondSurname) {

        return new User(
                null,
                username.toLowerCase(),
                rawPassword,
                email,
                names,
                firstSurname,
                secondSurname,
                LocalDateTime.now(),
                null,
                true,
                true,
                new HashSet<>()
        );
    }


    public void updateNames(String names, String firstSurname, String secondSurname) {
        this.names = names;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
    }

    public void changeUsername(String username) {
        this.username = username.toLowerCase();
    }

    public void changeEmail(String email) {
        this.email = email;
    }

    public void changePassword(String password) {
        this.password = password;
        this.lastPasswordChange = LocalDateTime.now();
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

    public  void hide() {
        this.visible = false;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }

    public void cleanRoles() {
        this.roles.clear();
    }
}
