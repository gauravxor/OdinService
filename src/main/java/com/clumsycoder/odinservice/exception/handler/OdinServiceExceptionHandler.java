package com.clumsycoder.odinservice.exception.handler;

import com.clumsycoder.controlshift.commons.odinservice.ErrorCode;
import com.clumsycoder.controlshift.commons.response.ApiError;
import com.clumsycoder.odinservice.exception.auth.AuthenticationException;
import com.clumsycoder.odinservice.exception.auth.InvalidCredentialsException;
import com.clumsycoder.odinservice.exception.auth.JwtException;
import com.clumsycoder.odinservice.exception.auth.TokenExpiredException;
import com.clumsycoder.odinservice.exception.auth.UnauthorizedAccessException;
import com.clumsycoder.odinservice.exception.base.OdinServiceException;
import com.clumsycoder.odinservice.exception.service.OtpServiceException;
import com.clumsycoder.odinservice.exception.verification.OtpException;
import com.clumsycoder.odinservice.exception.verification.OtpExpiredException;
import com.clumsycoder.odinservice.exception.verification.OtpInvalidException;
import com.clumsycoder.odinservice.exception.verification.OtpMaxAttemptsExceededException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(1)
public class OdinServiceExceptionHandler {

    /**
     * Authentication related exception handlers
     */
    @ExceptionHandler(exception = InvalidCredentialsException.class)
    public ResponseEntity<ApiError> handleInvalidCredentialsException(InvalidCredentialsException e) {
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .message(e.getMessage()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(JwtException e) {
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .message(e.getMessage()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ApiError> handleTokenExpiredException(TokenExpiredException e) {
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .message(e.getMessage()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ApiError> handleUnauthorizedAccessException(UnauthorizedAccessException e) {
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.FORBIDDEN.value())
                        .message(e.getMessage()),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException e) {
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .message(e.getMessage()),
                HttpStatus.UNAUTHORIZED
        );
    }

    /**
     * Verification related exception hanlders
     */

    @ExceptionHandler(OtpException.class)
    public ResponseEntity<ApiError> handleOtpException(OtpException e) {
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage())
                        .errorCode(ErrorCode.OTP_ERROR.name()),
                HttpStatus.BAD_REQUEST
        );
    }


    @ExceptionHandler(OtpExpiredException.class)
    public ResponseEntity<ApiError> handleOtpExpiredException(OtpExpiredException e) {
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage())
                        .errorCode(ErrorCode.OTP_ERROR.name()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(OtpInvalidException.class)
    public ResponseEntity<ApiError> handleOtpInvalidException(OtpInvalidException e) {
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage())
                        .errorCode(ErrorCode.OTP_ERROR.name()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(OtpMaxAttemptsExceededException.class)
    public ResponseEntity<ApiError> handleOtpMaxAttemptsExceededException(OtpMaxAttemptsExceededException e) {
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.TOO_MANY_REQUESTS.value())
                        .message(e.getMessage())
                        .errorCode(ErrorCode.OTP_ERROR.name()),
                HttpStatus.TOO_MANY_REQUESTS
        );
    }

    /**
     * Fallback service level exception
     */
    @ExceptionHandler(OtpServiceException.class)
    public ResponseEntity<ApiError> handleOtpServiceException(OtpServiceException e) {
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(e.getMessage())
                        .errorCode(ErrorCode.OTP_ERROR.name()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(OdinServiceException.class)
    public ResponseEntity<ApiError> handleOdinServiceException(OdinServiceException e) {
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(e.getMessage())
                        .errorCode(ErrorCode.INTERNAL_ERROR.name()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}