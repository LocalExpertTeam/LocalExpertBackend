package com.local_expert.backend.account_management.authentication;

import com.local_expert.backend.enums.AccountType;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CustomUserDetailsTest {

    private static final Long ID = 1L;
    private static final String MAIL = "lolek@gmail.com";
    private static final String PASSWORD = "Password123*";
    private static final AccountType ACCOUNT_TYPE = AccountType.CUSTOMER;
    private static final CustomUserDetails ACTIVE_USER =
            new CustomUserDetails(ID, MAIL, PASSWORD, ACCOUNT_TYPE, true);
    private static final CustomUserDetails INACTIVE_USER =
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