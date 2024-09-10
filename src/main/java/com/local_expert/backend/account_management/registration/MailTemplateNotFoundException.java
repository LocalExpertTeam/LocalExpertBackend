package com.local_expert.backend.account_management.registration;

import com.local_expert.backend.exception.ApiRequestException;
import org.springframework.http.HttpStatus;

class MailTemplateNotFoundException extends ApiRequestException {

    public MailTemplateNotFoundException(String message, Throwable cause) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }
}
