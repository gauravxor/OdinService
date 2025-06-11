package com.clumsycoder.odinservice.exception.auth;

import com.clumsycoder.odinservice.constants.ErrorMessages;

public class InvalidCredentialsException extends AuthenticationException {
    public InvalidCredentialsException() {
        super(ErrorMessages.INVALID_CREDENTIALS);
    }

    public InvalidCredentialsException(Throwable cause) {
        super(ErrorMessages.INVALID_CREDENTIALS, cause);
    }

    public InvalidCredentialsException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidCredentialsException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}