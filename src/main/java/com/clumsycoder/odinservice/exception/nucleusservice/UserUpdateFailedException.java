package com.clumsycoder.odinservice.exception.nucleusservice;

import com.clumsycoder.odinservice.constants.ErrorMessages;

public class UserUpdateFailedException extends NucleusServiceException {
    public UserUpdateFailedException() {
        super(ErrorMessages.USER_UPDATE_FAILED);
    }

    public UserUpdateFailedException(Throwable cause) {
        super(ErrorMessages.USER_UPDATE_FAILED, cause);
    }

    public UserUpdateFailedException(String errorMessage) {
        super(errorMessage);
    }

    public UserUpdateFailedException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}