package com.clumsycoder.odinservice.exception.auth;

import com.clumsycoder.odinservice.constants.ErrorMessages;

public class UnauthorizedAccessException extends AuthenticationException {
    public UnauthorizedAccessException() {
        super(ErrorMessages.UNAUTHORIZED_ACCESS);
    }

    public UnauthorizedAccessException(Throwable cause) {
        super(ErrorMessages.UNAUTHORIZED_ACCESS, cause);
    }

    public UnauthorizedAccessException(String errorMessage) {
        super(errorMessage);
    }

    public UnauthorizedAccessException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}