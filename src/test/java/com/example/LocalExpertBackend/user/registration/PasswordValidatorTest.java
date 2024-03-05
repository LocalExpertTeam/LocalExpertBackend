package com.example.LocalExpertBackend.user.registration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"pass1", "Password1", "pass1*", "pass123"})
    void shouldReturnTrueIfContainsAtLeastOneDigit(String password) {
        //Given

        //When
        boolean containsAtLeastOneDigit = PasswordValidator.containsAtLeastOneDigit(password);

        //Then
        assertTrue(containsAtLeastOneDigit);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "Password", "pass*", "password"})
    void shouldNotReturnTrueIfNotContainAtLeastOneDigit(String password) {
        //Given

        //When
        boolean containsAtLeastOneDigit = PasswordValidator.containsAtLeastOneDigit(password);

        //Then
        assertFalse(containsAtLeastOneDigit);
    }

    @ParameterizedTest
    @ValueSource(strings = {"P", "Password1", "Pass1*", "pAss123"})
    void shouldReturnTrueIfContainsAtLeastOneUppercaseLetter(String password) {
        //Given

        //When
        boolean containsAtLeastOneUppercase = PasswordValidator.containsAtLeastOneUppercaseLetter(password);

        //Then
        assertTrue(containsAtLeastOneUppercase);
    }

    @ParameterizedTest
    @ValueSource(strings = {"p", "password1", "pass1*", "123"})
    void shouldReturnFalseIfNotContainAtLeastOneUppercaseLetter(String password) {
        //Given

        //When
        boolean containsAtLeastOneUppercase = PasswordValidator.containsAtLeastOneUppercaseLetter(password);

        //Then
        assertFalse(containsAtLeastOneUppercase);
    }

    @ParameterizedTest
    @ValueSource(strings = {"p", "Password1", "Pass1*", "pAss123"})
    void shouldReturnTrueIfContainsAtLeastOneLowercaseLetter(String password) {
        //Given

        //When
        boolean containsAtLeastOneLowercase = PasswordValidator.containsAtLeastOneLowercaseLetter(password);

        //Then
        assertTrue(containsAtLeastOneLowercase);
    }

    @ParameterizedTest
    @ValueSource(strings = {"P", "PASS1", "PASS1*", "123"})
    void shouldReturnFalseIfNotContainAtLeastOneLowercaseLetter(String password) {
        //Given

        //When
        boolean containsAtLeastOneLowercase = PasswordValidator.containsAtLeastOneLowercaseLetter(password);

        //Then
        assertFalse(containsAtLeastOneLowercase);
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "Password1*", "pass1*", "pAss*"})
    void shouldReturnTrueIfContainsAtLeastOneSpecialCharacter(String password) {
        //Given

        //When
        boolean containsAtLeastOneSpecialCharacter = PasswordValidator.containsAtLeastOneSpecialCharacter(password);

        //Then
        assertTrue(containsAtLeastOneSpecialCharacter);
    }

    @ParameterizedTest
    @ValueSource(strings = {"P", "Password1", "pass1", "pAss"})
    void shouldReturnFalseIfNotContainAtLeastOneSpecialCharacter(String password) {
        //Given

        //When
        boolean containsAtLeastOneSpecialCharacter = PasswordValidator.containsAtLeastOneSpecialCharacter(password);

        //Then
        assertFalse(containsAtLeastOneSpecialCharacter);
    }
}