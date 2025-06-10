package com.clumsycoder.odinservice.exception.nucleusservice;

import com.clumsycoder.odinservice.constants.ErrorMessages;

public class NucleusValidationException extends NucleusServiceException {
    public NucleusValidationException() {
        super(ErrorMessages.VALIDATION_ERROR);
    }

    public NucleusValidationException(Throwable cause) {
        super(ErrorMessages.VALIDATION_ERROR, cause);
    }

    public NucleusValidationException(String errorMessage) {
        super(errorMessage);
    }

    public NucleusValidationException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}