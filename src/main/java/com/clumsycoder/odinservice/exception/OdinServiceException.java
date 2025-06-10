package com.clumsycoder.odinservice.exception;

import com.clumsycoder.odinservice.constants.ErrorMessages;

/**
 * This exception is a base exception for Odin Service
 */
public class OdinServiceException extends RuntimeException {
    OdinServiceException() {
        super(ErrorMessages.ODIN_SERVICE_ERROR);
    }

    public OdinServiceException(Throwable cause) {
        super(ErrorMessages.ODIN_SERVICE_ERROR, cause);
    }

    public OdinServiceException(String errorMessage) {
        super(errorMessage);
    }

    public OdinServiceException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}