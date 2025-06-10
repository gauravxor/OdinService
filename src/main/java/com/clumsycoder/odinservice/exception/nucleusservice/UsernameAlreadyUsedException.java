package com.clumsycoder.odinservice.exception.nucleusservice;

import com.clumsycoder.odinservice.constants.ErrorMessages;

public class UsernameAlreadyUsedException extends NucleusServiceException {

    public UsernameAlreadyUsedException() {
        super(ErrorMessages.USERNAME_ALREADY_USED);
    }

    public UsernameAlreadyUsedException(Throwable cause) {
        super(ErrorMessages.USERNAME_ALREADY_USED, cause);
    }

    public UsernameAlreadyUsedException(String errorMessage) {
        super(errorMessage);
    }

    public UsernameAlreadyUsedException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}