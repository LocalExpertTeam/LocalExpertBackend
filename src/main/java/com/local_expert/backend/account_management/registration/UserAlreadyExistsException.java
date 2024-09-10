package com.local_expert.backend.account_management.registration;

import com.local_expert.backend.exception.ApiRequestException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ApiRequestException {

    public UserAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
