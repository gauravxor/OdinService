package com.clumsycoder.odinservice.exception.auth;

import com.clumsycoder.odinservice.exception.base.OdinServiceException;

public class AuthenticationException extends OdinServiceException {
    public AuthenticationException(String errorMessage) {
        super(errorMessage);
    }

    public AuthenticationException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}