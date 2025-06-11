package com.clumsycoder.odinservice.exception.verification;

import com.clumsycoder.odinservice.constants.ErrorMessages;

public class OtpInvalidException extends OtpException {
    public OtpInvalidException() {
        super(ErrorMessages.OTP_INVALID);
    }

    public OtpInvalidException(Throwable cause) {
        super(ErrorMessages.OTP_INVALID, cause);
    }

    public OtpInvalidException(String errorMessage) {
        super(errorMessage);
    }

    public OtpInvalidException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}