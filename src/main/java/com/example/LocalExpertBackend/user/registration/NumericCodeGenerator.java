package com.example.LocalExpertBackend.user.registration;

public class NumericCodeGenerator {

    static String getNumericCode(int n)
    {
        String AlphaNumericString = "0123456789";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}
