package com.clumsycoder.odinservice.exception.base;

public class OdinServiceException extends RuntimeException {
    public OdinServiceException(String errorMessage) {
        super(errorMessage);
    }

    public OdinServiceException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}