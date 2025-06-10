package com.clumsycoder.odinservice.exception;

import com.clumsycoder.odinservice.constants.ErrorMessages;

public class OtpException extends OdinServiceException {

    OtpException() {
        super(ErrorMessages.OTP_ERROR);
    }

    public OtpException(Throwable cause) {
        super(ErrorMessages.OTP_ERROR, cause);
    }

    public OtpException(String errorMessage) {
        super(errorMessage);
    }

    public OtpException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}