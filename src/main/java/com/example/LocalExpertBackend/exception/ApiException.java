package com.example.LocalExpertBackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ApiException {
    private final String msq;
    private final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;
}
