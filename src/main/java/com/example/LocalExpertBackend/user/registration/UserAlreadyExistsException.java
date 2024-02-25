package com.example.LocalExpertBackend.user.registration;

import com.example.LocalExpertBackend.exception.ApiRequestException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ApiRequestException {

    public UserAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
