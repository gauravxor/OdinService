package com.clumsycoder.odinservice.exception.auth;

import com.clumsycoder.odinservice.constants.ErrorMessages;

public class TokenExpiredException extends AuthenticationException {
    public TokenExpiredException() {
        super(ErrorMessages.TOKEN_EXPIRED);
    }

    public TokenExpiredException(Throwable cause) {
        super(ErrorMessages.TOKEN_EXPIRED, cause);
    }

    public TokenExpiredException(String errorMessage) {
        super(errorMessage);
    }

    public TokenExpiredException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}