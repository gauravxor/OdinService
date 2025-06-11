package com.clumsycoder.odinservice.exception.user;

import com.clumsycoder.odinservice.constants.ErrorMessages;
import com.clumsycoder.odinservice.exception.base.OdinServiceException;

public class UserAlreadyExists extends OdinServiceException {
    public UserAlreadyExists() {
        super(ErrorMessages.USER_ALREADY_EXISTS);
    }

    public UserAlreadyExists(Throwable cause) {
        super(ErrorMessages.USER_ALREADY_EXISTS, cause);
    }

    public UserAlreadyExists(String errorMessage) {
        super(errorMessage);
    }

    public UserAlreadyExists(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}