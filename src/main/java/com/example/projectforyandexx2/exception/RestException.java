package com.example.projectforyandexx2.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RestException extends RuntimeException {
    private HttpStatus httpStatus;

    public RestException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }
}
