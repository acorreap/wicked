package cl.acorreap.backend.common.formatter;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class AuthorityNameFormatter {

    public static final Pattern CAMEL_CASE_PATTERN = Pattern.compile("([a-z])([A-Z])");

    public static final Pattern NON_ALPHANUMERIC = Pattern.compile("[^a-zA-Z0-9]+");

    public static final Pattern LETTER_TO_NUMBER = Pattern.compile("([a-zA-Z])([0-9])");
    public static final Pattern NUMBER_TO_LETTER = Pattern.compile("([0-9])([a-zA-Z])");

    public static String format(String raw) {
        if (raw == null || raw.isBlank()) {
            throw new IllegalArgumentException("Authority name cannot be null or empty");
        }

        String normalized = NON_ALPHANUMERIC.matcher(raw).replaceAll("_");

        normalized = CAMEL_CASE_PATTERN.matcher(normalized).replaceAll("$1_$2");

        normalized = LETTER_TO_NUMBER.matcher(normalized).replaceAll("$1_$2");

        normalized = NUMBER_TO_LETTER.matcher(normalized).replaceAll("$1_$2");

        normalized = normalized.toUpperCase();

        normalized = normalized.replaceAll("__+", "_");

        return normalized.replaceAll("^_|_$", "");
    }
}
