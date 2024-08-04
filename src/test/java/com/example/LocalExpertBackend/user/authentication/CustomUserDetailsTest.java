package com.example.LocalExpertBackend.user.authentication;

import com.example.LocalExpertBackend.enums.AccountType;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CustomUserDetailsTest {

    private final static Long ID = 1L;
    private final static String MAIL = "lolek@gmail.com";
    private final static String PASSWORD = "Password123*";
    private final static AccountType ACCOUNT_TYPE = AccountType.CUSTOMER;
    private final static CustomUserDetails ACTIVE_USER =
            new CustomUserDetails(ID, MAIL, PASSWORD, ACCOUNT_TYPE, true);
    private final static CustomUserDetails INACTIVE_USER =
            new CustomUserDetails(ID, MAIL, PASSWORD, ACCOUNT_TYPE, false);

    @Test
    void userShouldBeActive() {
        //Given

        //When

        //Then
        assertTrue(ACTIVE_USER.isActive());
        assertTrue(ACTIVE_USER.isAccountNonExpired());
        assertTrue(ACTIVE_USER.isAccountNonLocked());
        assertTrue(ACTIVE_USER.isCredentialsNonExpired());
        assertEquals(Collections.emptySet(), ACTIVE_USER.getAuthorities());
    }

    @Test
    void userShouldBeInActive() {
        //Given

        //When

        //Then
        assertFalse(INACTIVE_USER.isActive());
        assertTrue(INACTIVE_USER.isAccountNonExpired());
        assertTrue(INACTIVE_USER.isAccountNonLocked());
        assertTrue(INACTIVE_USER.isCredentialsNonExpired());
        assertEquals(Collections.emptySet(), ACTIVE_USER.getAuthorities());
    }
}