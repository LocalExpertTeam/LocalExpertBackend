package com.local_expert.backend.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class ApiExceptionHandlerTest {

    @Test
    void shouldHandleApiExceptionWithMessage() {
        //Given
        ApiExceptionHandler apiExceptionHandler = new ApiExceptionHandler();
        ApiRequestException exception = new ApiRequestException("Api request exception", HttpStatus.BAD_REQUEST);

        //When
        ResponseEntity<Object> response = apiExceptionHandler.handlerApiRequestException(exception);

        //Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldHandleApiExceptionWithMessageAndCause() {
        //Given
        ApiExceptionHandler apiExceptionHandler = new ApiExceptionHandler();
        RuntimeException runtimeException = new RuntimeException();
        String apiRequestExceptionMessage = "Api request exception";
        ApiRequestException exception =
                new ApiRequestException(apiRequestExceptionMessage, HttpStatus.BAD_REQUEST, runtimeException);

        //When
        ResponseEntity<Object> response = apiExceptionHandler.handlerApiRequestException(exception);
        ApiExceptionMessage responseBody = (ApiExceptionMessage) response.getBody();

        //Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(responseBody);
        assertEquals(apiRequestExceptionMessage, responseBody.getMsg());
    }
}