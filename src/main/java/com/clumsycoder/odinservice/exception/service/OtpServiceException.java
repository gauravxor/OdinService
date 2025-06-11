package com.clumsycoder.odinservice.exception.service;

import com.clumsycoder.odinservice.constants.ErrorMessages;
import com.clumsycoder.odinservice.exception.base.OdinServiceException;

public class OtpServiceException extends OdinServiceException {
    public OtpServiceException() {
        super(ErrorMessages.OTP_SERVICE_ERROR);
    }

    public OtpServiceException(Throwable cause) {
        super(ErrorMessages.OTP_SERVICE_ERROR, cause);
    }

    public OtpServiceException(String errorMessage) {
        super(errorMessage);
    }

    public OtpServiceException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}