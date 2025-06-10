package com.clumsycoder.odinservice.exception.nucleusservice;

import com.clumsycoder.odinservice.constants.ErrorMessages;

public class EmailAlreadyUsedException extends NucleusServiceException {

    public EmailAlreadyUsedException() {
        super(ErrorMessages.EMAIL_ALREADY_USED);
    }

    public EmailAlreadyUsedException(Throwable cause) {
        super(ErrorMessages.EMAIL_ALREADY_USED, cause);
    }

    public EmailAlreadyUsedException(String errorMessage) {
        super(errorMessage);
    }

    public EmailAlreadyUsedException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}