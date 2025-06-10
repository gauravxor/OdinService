package com.clumsycoder.odinservice.exception.nucleusservice;

import com.clumsycoder.odinservice.constants.ErrorMessages;

public class UserDeleteFailedException extends NucleusServiceException {
    public UserDeleteFailedException() {
        super(ErrorMessages.USER_DELETE_FAILED);
    }

    public UserDeleteFailedException(Throwable cause) {
        super(ErrorMessages.USER_DELETE_FAILED, cause);
    }

    public UserDeleteFailedException(String errorMessage) {
        super(errorMessage);
    }

    public UserDeleteFailedException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}