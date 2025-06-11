package com.clumsycoder.odinservice.exception.user;

import com.clumsycoder.odinservice.constants.ErrorMessages;
import com.clumsycoder.odinservice.exception.base.OdinServiceException;

public class UserNotFoundException extends OdinServiceException {

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