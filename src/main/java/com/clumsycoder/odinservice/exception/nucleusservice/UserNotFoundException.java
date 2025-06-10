package com.clumsycoder.odinservice.exception.nucleusservice;

import com.clumsycoder.odinservice.constants.ErrorMessages;

public class UserNotFoundException extends NucleusServiceException {
    public UserNotFoundException() {
        super(ErrorMessages.USER_NOT_FOUND);
    }

    public UserNotFoundException(Throwable cause) {
        super(ErrorMessages.USER_NOT_FOUND, cause);
    }

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public UserNotFoundException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}