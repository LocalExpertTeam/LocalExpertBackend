package com.example.LocalExpertBackend.user.registration;

import com.example.LocalExpertBackend.exception.ApiRequestException;
import org.springframework.http.HttpStatus;

class MailTemplateNotFoundException extends ApiRequestException {

    public MailTemplateNotFoundException(String message, Throwable cause) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }
}
