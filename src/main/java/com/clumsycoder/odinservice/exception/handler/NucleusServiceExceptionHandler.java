package com.clumsycoder.odinservice.exception.handler;

import com.clumsycoder.controlshift.commons.nucleusservice.ErrorCode;
import com.clumsycoder.controlshift.commons.response.ApiError;
import com.clumsycoder.controlshift.commons.response.ApiResponse;
import com.clumsycoder.odinservice.exception.nucleusservice.EmailAlreadyUsedException;
import com.clumsycoder.odinservice.exception.nucleusservice.NucleusServiceException;
import com.clumsycoder.odinservice.exception.nucleusservice.NucleusValidationException;
import com.clumsycoder.odinservice.exception.nucleusservice.UserCreateFailedException;
import com.clumsycoder.odinservice.exception.nucleusservice.UserDeleteFailedException;
import com.clumsycoder.odinservice.exception.nucleusservice.UserNotFoundException;
import com.clumsycoder.odinservice.exception.nucleusservice.UserUpdateFailedException;
import com.clumsycoder.odinservice.exception.nucleusservice.UsernameAlreadyUsedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NucleusServiceExceptionHandler {

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<ApiError> handleEmailAlreadyUsedException(EmailAlreadyUsedException e) {
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.EMAIL_CONFLICT.name()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(UsernameAlreadyUsedException.class)
    public ResponseEntity<ApiError> handleUsernameAlreadyUsedException(UsernameAlreadyUsedException e) {
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.USERNAME_CONFLICT.name()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(NucleusValidationException.class)
    public ResponseEntity<ApiError> handleNucleusValidationException(NucleusValidationException e) {
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.VALIDATION_ERROR.name()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserCreateFailedException.class)
    public ResponseEntity<ApiError> handleUserCreateFailed(UserCreateFailedException e) {
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.USER_CREATE_FAILED.name()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(UserUpdateFailedException.class)
    public ResponseEntity<ApiError> handleUserUpdateFailedException(UserUpdateFailedException e) {
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.USER_UPDATE_FAILED.name()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(UserDeleteFailedException.class)
    public ResponseEntity<ApiError> handleUserDeleteFailedException(UserDeleteFailedException e) {
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.USER_DELETE_FAILED.name()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.USER_NOT_FOUND.name()),
                HttpStatus.NOT_FOUND
        );
    }


    @ExceptionHandler(NucleusServiceException.class)
    public ResponseEntity<ApiResponse> handleNucleusServiceException(NucleusServiceException e) {
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()).errorCode(ErrorCode.INTERNAL_ERROR.name()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}