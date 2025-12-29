package cl.acorreap.backend.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final String exception;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public ErrorResponse(
            int status,
            String error,
            String message,
            String path,
            String exception
    ) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
        this.exception = exception;
    }

    public static ErrorResponse of(
            int status,
            String error,
            String message,
            String path,
            Exception exception
    ) {
        return new ErrorResponse(status, error, message, path, exception.getClass().getSimpleName());
    }
}
