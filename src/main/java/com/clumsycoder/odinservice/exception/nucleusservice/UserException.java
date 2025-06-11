package com.clumsycoder.odinservice.exception.nucleusservice;

import com.clumsycoder.odinservice.constants.ErrorMessages;

public class UserException extends NucleusServiceException {
    public UserException() {
        super(ErrorMessages.USER_ERROR);
    }

    public UserException(Throwable cause) {
        super(ErrorMessages.USER_ERROR, cause);
    }

    public UserException(String errorMessage) {
        super(errorMessage);
    }

    public UserException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}