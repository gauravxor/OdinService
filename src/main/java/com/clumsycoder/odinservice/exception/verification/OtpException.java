package com.clumsycoder.odinservice.exception.verification;

import com.clumsycoder.odinservice.constants.ErrorMessages;
import com.clumsycoder.odinservice.exception.base.OdinServiceException;

public class OtpException extends OdinServiceException {
    public OtpException() {
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