package com.clumsycoder.odinservice.exception.verification;

import com.clumsycoder.odinservice.constants.ErrorMessages;

public class OtpMaxAttemptsExceededException extends OtpException {
    public OtpMaxAttemptsExceededException() {
        super(ErrorMessages.OTP_MAX_ATTEMPT_EXCEEDED);
    }

    public OtpMaxAttemptsExceededException(Throwable cause) {
        super(ErrorMessages.OTP_MAX_ATTEMPT_EXCEEDED, cause);
    }

    public OtpMaxAttemptsExceededException(String errorMessage) {
        super(errorMessage);
    }

    public OtpMaxAttemptsExceededException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}