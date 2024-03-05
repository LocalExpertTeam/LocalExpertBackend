package com.example.LocalExpertBackend.user.registration;

import lombok.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    private static final Pattern atLeastOneDigitPattern = Pattern.compile(".*\\d.*");
    private static final Pattern atLeastOneUppercaseLetterPattern = Pattern.compile(".*[A-Z].*");
    private static final Pattern atLeastOneLowercasePattern = Pattern.compile(".*[a-z].*");
    private static final Pattern atLeastOneSpecialCharacterPattern = Pattern.compile(".*[#?!@$%^&*-].*");

    static boolean containsAtLeastOneDigit(@NonNull String password) {
        Matcher matcher = atLeastOneDigitPattern.matcher(password);
        return matcher.matches();
    }

    static boolean containsAtLeastOneUppercaseLetter(@NonNull String password) {
        Matcher matcher = atLeastOneUppercaseLetterPattern.matcher(password);
        return matcher.matches();
    }

    static boolean containsAtLeastOneLowercaseLetter(@NonNull String password) {
        Matcher matcher = atLeastOneLowercasePattern.matcher(password);
        return matcher.matches();
    }

    static boolean containsAtLeastOneSpecialCharacter(@NonNull String password) {
        Matcher matcher = atLeastOneSpecialCharacterPattern.matcher(password);
        return matcher.matches();
    }

}
