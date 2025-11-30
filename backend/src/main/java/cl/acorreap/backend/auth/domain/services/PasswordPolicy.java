package cl.acorreap.backend.auth.domain.services;

public class PasswordPolicy {

    private static final int MIN_LENGTH = 8;

    public boolean isValid(String password) {
        if (password == null) {
            return false;
        }
        String pattern = String.format("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{%d,}$", MIN_LENGTH);
        return password.matches(pattern);
    }
}
