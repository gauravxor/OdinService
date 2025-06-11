package com.clumsycoder.odinservice.exception.verification;

import com.clumsycoder.odinservice.constants.ErrorMessages;

public class OtpExpiredException extends OtpException {
    public OtpExpiredException() {
        super(ErrorMessages.OTP_EXPIRED);
    }

    public OtpExpiredException(Throwable cause) {
        super(ErrorMessages.OTP_EXPIRED, cause);
    }

    public OtpExpiredException(String errorMessage) {
        super(errorMessage);
    }

    public OtpExpiredException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}