package com.example.LocalExpertBackend.user.registration;

import com.example.LocalExpertBackend.exception.ApiRequestException;
import org.springframework.http.HttpStatus;

class WrongPasswordFormatException extends ApiRequestException {

    public WrongPasswordFormatException(String message) {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
