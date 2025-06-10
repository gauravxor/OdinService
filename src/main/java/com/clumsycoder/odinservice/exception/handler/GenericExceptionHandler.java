package com.clumsycoder.odinservice.exception.handler;

import com.clumsycoder.controlshift.commons.response.ApiError;
import com.clumsycoder.controlshift.commons.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHandler {
    @ExceptionHandler(exception = RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception e) {
        return new ResponseEntity<>(
                new ApiError().message(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}