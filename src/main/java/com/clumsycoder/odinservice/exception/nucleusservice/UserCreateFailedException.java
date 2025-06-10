package com.clumsycoder.odinservice.exception.nucleusservice;

import com.clumsycoder.odinservice.constants.ErrorMessages;

public class UserCreateFailedException extends NucleusServiceException {
    public UserCreateFailedException() {
        super(ErrorMessages.USER_CREATE_FAILED);
    }

    public UserCreateFailedException(Throwable cause) {
        super(ErrorMessages.USER_CREATE_FAILED, cause);
    }

    public UserCreateFailedException(String errorMessage) {
        super(errorMessage);
    }

    public UserCreateFailedException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}