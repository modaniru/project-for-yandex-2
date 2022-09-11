package com.example.projectforyandexx2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(RestException.class)
    public ResponseEntity<Object> handleRestExceptionException(RestException restException) {
        HttpStatus httpStatus = restException.getStatus();
        ApiResponseException apiException = ApiResponseException.builder()
                .message(restException.getMessage())
                .code(httpStatus.value()).build();
        return new ResponseEntity<>(apiException, httpStatus);
    }
}
