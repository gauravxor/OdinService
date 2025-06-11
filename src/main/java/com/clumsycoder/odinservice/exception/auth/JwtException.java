package com.clumsycoder.odinservice.exception.auth;

import com.clumsycoder.odinservice.constants.ErrorMessages;

public class JwtException extends AuthenticationException {
    public JwtException() {
        super(ErrorMessages.JWT_ERROR);
    }

    public JwtException(Throwable cause) {
        super(ErrorMessages.JWT_ERROR, cause);
    }

    public JwtException(String errorMessage) {
        super(errorMessage);
    }

    public JwtException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}