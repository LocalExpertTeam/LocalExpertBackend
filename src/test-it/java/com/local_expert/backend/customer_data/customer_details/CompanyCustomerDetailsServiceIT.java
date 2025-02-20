package com.local_expert.backend.customer_data.customer_details;

import com.local_expert.backend.enums.AccountType;
import com.local_expert.backend.enums.ContactMethod;
import com.local_expert.backend.account_management.authentication.CustomUserDetails;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CompanyCustomerDetailsServiceIT {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest");

    public static final String FIRST_NAME = "Jerzy";
    public static final String LAST_NAME = "Kowalski";
    public static final String PHONE = "999999183";
    public static final String PROFILE = null;
    public static final String MAIL = "mat@gmail.com";
    public static final LocalDate CREATION_DATE = LocalDate.of(2023, 10, 4);
    public static final String PREFERRED_CONTACT_TIME = "Pn - Pt 8 - 16";
    public static final ContactMethod PREFERRED_CONTACT_METHOD = ContactMethod.PHONE;
    public static final CustomUserDetails COMPANY_USER = CustomUserDetails.builder()
                                                                        .id(1L)
                                                                        .accountType(AccountType.COMPANY)
                                                                        .build();
    public static final CustomUserDetails COMPANY_PREMIUM_USER = CustomUserDetails.builder()
                                                                        .id(1L)
                                                                        .accountType(AccountType.COMPANY_PREMIUM)
                                                                        .build();
    public static final CustomUserDetails CUSTOMER_USER = CustomUserDetails.builder()
                                                                        .id(1L)
                                                                        .accountType(AccountType.CUSTOMER)
                                                                        .build();

    @Autowired
    private CompanyCustomerDetailsCustomerRepository repository;

    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        mySQLContainer.stop();
    }

    @Test
    @Sql("/db-scripts/customer_data/customer-details-service-it.sql")
    @Transactional
    void shouldReturnCustomerDetailsWithProperDataWhenUserLoggedInAsCompany() {
        //Given
        CompanyCustomerDetailsService customerDetailsService = new CompanyCustomerDetailsService(repository);

        //When
        CustomerDetails customerDetails =
                customerDetailsService.getCustomerDetails(COMPANY_USER, 1);

        prepareCustomerDetails();

        //Then
        assertEquals(prepareCustomerDetails(), customerDetails);
    }

    @Test
    @Sql("/db-scripts/customer_data/customer-details-service-it.sql")
    @Transactional
    void shouldReturnCustomerDetailsWithProperDataWhenUserLoggedInAsCompanyPremium() {
        //Given
        CompanyCustomerDetailsService customerDetailsService = new CompanyCustomerDetailsService(repository);

        //When
        CustomerDetails customerDetails =
                customerDetailsService.getCustomerDetails(COMPANY_PREMIUM_USER, 1);

        prepareCustomerDetails();

        //Then
        assertEquals(prepareCustomerDetails(), customerDetails);
    }

    @Test
    @Sql("/db-scripts/customer_data/customer-details-service-it.sql")
    @Transactional
    void shouldThrowAccessDeniedExceptionWhenUserIsNotOnCompanyAccount() {
        //Given
        CompanyCustomerDetailsService customerDetailsService = new CompanyCustomerDetailsService(repository);

        //When
        AccessDeniedException exception =
                assertThrows(AccessDeniedException.class, () -> customerDetailsService.getCustomerDetails(CUSTOMER_USER, 1));

        prepareCustomerDetails();

        //Then
        assertEquals("Access denied for " + CUSTOMER_USER.getAccountType() + " account.", exception.getMessage());
        assertEquals(HttpStatus.FORBIDDEN, exception.getHttpStatusCode());
    }

    private CustomerDetails prepareCustomerDetails() {
        HashMap<Integer, Integer> ratesNumber = new HashMap<>();
        ratesNumber.put(1, 0);
        ratesNumber.put(2, 1);
        ratesNumber.put(3, 0);
        ratesNumber.put(4, 2);
        ratesNumber.put(5, 1);

        return CustomerDetails.builder()
                                .firstName(FIRST_NAME)
                                .lastName(LAST_NAME)
                                .profile(PROFILE)
                                .phone(PHONE)
                                .creationDate(CREATION_DATE)
                                .email(MAIL)
                                .preferredContactTime(PREFERRED_CONTACT_TIME)
                                .preferredContactMethod(PREFERRED_CONTACT_METHOD)
                                .ratesNumber(ratesNumber)
                                .ratesTotalNumber(4)
                                .build();
    }

}