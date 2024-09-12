package com.local_expert.backend.account_management.registration;

import com.local_expert.backend.exception.ApiRequestException;
import org.springframework.http.HttpStatus;

class WrongEmailFormatException extends ApiRequestException {

    public WrongEmailFormatException(String message) {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
