package com.example.LocalExpertBackend.company.customer_details;

import com.example.LocalExpertBackend.exception.ApiRequestException;
import org.springframework.http.HttpStatus;

public class AccessDeniedException extends ApiRequestException {

    public AccessDeniedException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
