package com.local_expert.backend.account_management.registration;

import com.local_expert.backend.exception.ApiRequestException;
import org.springframework.http.HttpStatus;

class WrongPasswordFormatException extends ApiRequestException {

    public WrongPasswordFormatException(String message) {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
