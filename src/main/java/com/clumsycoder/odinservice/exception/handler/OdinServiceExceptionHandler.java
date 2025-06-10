package com.clumsycoder.odinservice.exception.handler;

import com.clumsycoder.controlshift.commons.odinservice.ErrorCode;
import com.clumsycoder.controlshift.commons.response.ApiError;
import com.clumsycoder.odinservice.exception.JwtException;
import com.clumsycoder.odinservice.exception.OdinServiceException;
import com.clumsycoder.odinservice.exception.OtpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OdinServiceExceptionHandler {
    @ExceptionHandler(OtpException.class)
    public ResponseEntity<ApiError> handleOtpException(OtpException e) {
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.OTP_ERROR.name()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(JwtException e) {
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.JWT_ERROR.name()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(OdinServiceException.class)
    public ResponseEntity<ApiError> handleOdinServiceException(OdinServiceException e) {
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.INTERNAL_ERROR.name()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}