package com.example.LocalExpertBackend.user.registration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class EmailValidator {
    private static final Pattern emailPattern =
            Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    static boolean isInEmailFormat(String email) {
        if (email == null) return false;
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }
}
