package com.local_expert.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
class ApiExceptionMessage {
    private final String msg;
    private final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;
}
