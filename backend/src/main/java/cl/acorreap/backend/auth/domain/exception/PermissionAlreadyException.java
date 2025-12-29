package cl.acorreap.backend.auth.domain.exception;

import cl.acorreap.backend.common.exception.BusinessException;

public class PermissionAlreadyException extends BusinessException {
    public PermissionAlreadyException(String message) {
        super(message);
    }
}
