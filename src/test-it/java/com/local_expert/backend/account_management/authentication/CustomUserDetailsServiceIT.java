package com.local_expert.backend.account_management.authentication;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomUserDetailsServiceIT {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest");

    public static final String NOT_EXISTING_ACCOUNT_EMAIL = "paul@gmail.com";
    public static final String EXISTING_ACCOUNT_EMAIL = "mat@gmail.com";
    public static final String EXISTING_COMPANY_EMAIL = "mat2@gmail.com";
    public static final String EXISTING_ACCOUNT_TYPE = "CUSTOMER";
    public static final String EXISTING_COMPANY_TYPE = "COMPANY";
    private static final long ACCOUNT_ID = 1L;
    private static final long COMPANY_ID = 2L;

    @Autowired
    private UserAuthenticationAccountRepository repository;

    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        mySQLContainer.stop();
    }

    @Test
    @Sql("/db-scripts/account_management/authentication/user-authentication-service-it.sql")
    @Transactional
    void shouldFindUserCustomer() {
        //Given
        CustomUserDetailsService userDetailsService = new CustomUserDetailsService(repository);

        //When
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(EXISTING_ACCOUNT_EMAIL);

        //Then
        assertEquals(ACCOUNT_ID, userDetails.getId());
        assertEquals(EXISTING_ACCOUNT_EMAIL, userDetails.getMail());
        assertEquals(EXISTING_ACCOUNT_TYPE, userDetails.getAccountType().toString());
    }
    @Test
    @Sql("/db-scripts/account_management/authentication/user-authentication-service-it.sql")
    @Transactional
    void shouldFindUserCompany() {
        //Given
        CustomUserDetailsService userDetailsService = new CustomUserDetailsService(repository);

        //When
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(EXISTING_COMPANY_EMAIL);

        //Then
        assertEquals(COMPANY_ID, userDetails.getId());
        assertEquals(EXISTING_COMPANY_TYPE, userDetails.getAccountType().toString());
    }

    @Test
    @Sql("/db-scripts/account_management/authentication/user-authentication-service-it.sql")
    @Transactional
    void shouldThrowUsernameNotFoundException() {
        //Given
        CustomUserDetailsService userDetailsService = new CustomUserDetailsService(repository);

        //When
        UsernameNotFoundException usernameNotFoundException = assertThrows(UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(NOT_EXISTING_ACCOUNT_EMAIL));

        //Then
        assertEquals("Username " + NOT_EXISTING_ACCOUNT_EMAIL + " not found", usernameNotFoundException.getMessage());
    }

}