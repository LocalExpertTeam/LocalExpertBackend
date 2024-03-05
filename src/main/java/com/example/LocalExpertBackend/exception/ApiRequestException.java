package com.example.LocalExpertBackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiRequestException extends RuntimeException {

    private final HttpStatus httpStatusCode;

    public ApiRequestException(String message, HttpStatus httpStatus) {
        super(message);
        httpStatusCode = httpStatus;
    }

    public ApiRequestException(String message, HttpStatus httpStatus, Throwable cause) {
        super(message, cause);
        httpStatusCode = httpStatus;
    }
}
