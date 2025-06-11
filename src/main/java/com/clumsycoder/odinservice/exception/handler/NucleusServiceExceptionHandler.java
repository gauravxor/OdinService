package com.clumsycoder.odinservice.exception.handler;

import com.clumsycoder.controlshift.commons.nucleusservice.ErrorCode;
import com.clumsycoder.controlshift.commons.response.ApiError;
import com.clumsycoder.controlshift.commons.response.ApiResponse;
import com.clumsycoder.odinservice.exception.nucleusservice.EmailAlreadyUsedException;
import com.clumsycoder.odinservice.exception.nucleusservice.NucleusServiceException;
import com.clumsycoder.odinservice.exception.nucleusservice.NucleusValidationException;
import com.clumsycoder.odinservice.exception.nucleusservice.UserNotFoundException;
import com.clumsycoder.odinservice.exception.nucleusservice.UsernameAlreadyUsedException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(1)
public class NucleusServiceExceptionHandler {

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<ApiError> handleEmailAlreadyUsedException(EmailAlreadyUsedException e) {
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.CONFLICT.value())
                        .message(e.getMessage())
                        .errorCode(ErrorCode.EMAIL_CONFLICT.name()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(UsernameAlreadyUsedException.class)
    public ResponseEntity<ApiError> handleUsernameAlreadyUsedException(UsernameAlreadyUsedException e) {
        return new ResponseEntity<>(
                new ApiError()
                        .message(e.getMessage())
                        .statusCode(HttpStatus.CONFLICT.value())
                        .errorCode(ErrorCode.USERNAME_CONFLICT.name()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(NucleusValidationException.class)
    public ResponseEntity<ApiError> handleNucleusValidationException(NucleusValidationException e) {
        return new ResponseEntity<>(
                new ApiError()
                        .message(e.getMessage())
                        .statusCode(HttpStatus.CONFLICT.value())
                        .errorCode(ErrorCode.VALIDATION_ERROR.name()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message(e.getMessage())
                        .errorCode(ErrorCode.USER_NOT_FOUND.name()),
                HttpStatus.NOT_FOUND
        );
    }


    @ExceptionHandler(NucleusServiceException.class)
    public ResponseEntity<ApiResponse> handleNucleusServiceException(NucleusServiceException e) {
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(e.getMessage())
                        .errorCode(ErrorCode.INTERNAL_ERROR.name()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}