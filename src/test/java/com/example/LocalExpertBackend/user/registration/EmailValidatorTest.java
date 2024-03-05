package com.example.LocalExpertBackend.user.registration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    @Test
    void shouldNotThrowExceptionWheEmailInProperFormat() {
        //Given
        String validEmail = "lolek12@gmail.com";

        //When
        boolean isInEmailFormat = EmailValidator.isInEmailFormat(validEmail);

        //Then
        assertTrue(isInEmailFormat);
    }

    @ParameterizedTest
    @ValueSource(strings = {"@gmail.com", "lolek@.com", "lolek@", "lolekgmail.com", "lolekgmailcom",
                            "username.@gmail.com", ".user.name@gmail.com", "user-name@gmail.com."})
    @NullAndEmptySource
    void shouldThrowExceptionWheEmailInWrongFormats(String invalidEmail) {
        //Given

        //When
        boolean isInEmailFormat = EmailValidator.isInEmailFormat(invalidEmail);

        //Then
        assertFalse(isInEmailFormat, invalidEmail + " should be wrong email address.");
    }
}