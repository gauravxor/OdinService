package com.clumsycoder.odinservice.exception.handler;

import com.clumsycoder.controlshift.commons.odinservice.ErrorCode;
import com.clumsycoder.controlshift.commons.response.ApiError;
import com.clumsycoder.controlshift.commons.response.ApiResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Integer.MAX_VALUE)
public class GlobalExceptionHandler {
    @ExceptionHandler(exception = RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(e.getMessage())
                        .errorCode(ErrorCode.INTERNAL_ERROR.name()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception e) {
        return new ResponseEntity<>(
                new ApiError()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(e.getMessage())
                        .errorCode(ErrorCode.INTERNAL_ERROR.name()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}