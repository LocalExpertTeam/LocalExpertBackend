package com.example.LocalExpertBackend.user.registration;

import com.example.LocalExpertBackend.exception.ApiRequestException;
import org.springframework.http.HttpStatus;

class WrongEmailFormatException extends ApiRequestException {

    public WrongEmailFormatException(String message) {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
