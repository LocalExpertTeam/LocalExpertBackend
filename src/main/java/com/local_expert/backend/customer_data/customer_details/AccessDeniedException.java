package com.local_expert.backend.customer_data.customer_details;

import com.local_expert.backend.exception.ApiRequestException;
import org.springframework.http.HttpStatus;

public class AccessDeniedException extends ApiRequestException {

    public AccessDeniedException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
