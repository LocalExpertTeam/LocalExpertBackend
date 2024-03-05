package com.example.LocalExpertBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handlerApiRequestException(ApiRequestException exception) {
        ApiExceptionMessage apiExceptionMessage =
                new ApiExceptionMessage(exception.getMessage(), exception.getHttpStatusCode(),
                        ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiExceptionMessage, exception.getHttpStatusCode());
    }
}
