package com.clumsycoder.odinservice.exception.nucleusservice;

import com.clumsycoder.odinservice.constants.ErrorMessages;

/**
 * Base exception class for all the nucleus service related errors
 * Also used in handling the error code "INTERNAL_ERROR" from Nucleus Service
 * in feign client exception decoder
 */
public class NucleusServiceException extends RuntimeException {
    public NucleusServiceException() {
        super(ErrorMessages.NUCLEUS_SERVICE_ERROR);
    }

    public NucleusServiceException(Throwable cause) {
        super(ErrorMessages.NUCLEUS_SERVICE_ERROR, cause);
    }

    public NucleusServiceException(String errorMessage) {
        super(errorMessage);
    }

    public NucleusServiceException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}