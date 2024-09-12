package com.local_expert.backend.account_management.registration;

import com.local_expert.backend.enums.AccountType;
import com.local_expert.backend.exception.ApiRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAccountRegistrationServiceTest {

    public static final String USER_EMAIL = "paul@gmail.com";
    public static final String USER_PASSWORD = "Password123*";
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Cena";
    public static final String PHONE_NUMBER = "664664512";
    public static final String COMPANY_NAME = "Bulldozaur sp. z o.o.";
    @Mock
    private UserAccountRegistrationAccountRepository repository;
    @Mock
    private UserAccountRegistrationCompanyRepository companyRepository;
    @Mock
    private UserAccountRegistrationCustomerRepository customerRepository;
    @Mock
    private UserAccountRegistrationConfirmationCodeRepository confirmationCodeRepository;
    @Mock
    private ApplicationEventPublisher publisher;

    @Test
    void shouldSaveNewUserAccount() {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                                                    confirmationCodeRepository, publisher);
        when(repository.findByMail(anyString())).thenReturn(Optional.empty());

        //When
        service.createNewUserAccount(UserDAO.builder()
                                            .firstName(FIRST_NAME)
                                            .lastName(LAST_NAME)
                                            .mail(USER_EMAIL)
                                            .phoneNumber(PHONE_NUMBER)
                                            .password(USER_PASSWORD)
                                            .accountType(AccountType.CUSTOMER)
                                            .build());

        //Then
        verify(repository, times(1)).save(any(UserAccountRegistrationAccountEntity.class));
        verify(confirmationCodeRepository,
                times(1)).save(any(UserAccountRegistrationConfirmationCodeEntity.class));
        verify(publisher, times(1)).publishEvent(any(OnRegistrationCompleteEvent.class));
    }

    @ParameterizedTest
    @MethodSource("provideWrongEmailAddresses")
    void shouldThrowExceptionWhenEmailIsInWrongFormat(String emailAddress) {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                                                    confirmationCodeRepository, publisher);

        //When
        Exception exception =
                assertThrows(WrongEmailFormatException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                                                .firstName(FIRST_NAME)
                                                                .lastName(LAST_NAME)
                                                                .mail(emailAddress)
                                                                .phoneNumber(PHONE_NUMBER)
                                                                .password(USER_PASSWORD)
                                                                .accountType(AccountType.CUSTOMER)
                                                                .build()));

        //Then
        assertEquals("Email address has given in wrong format.", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("providePasswords")
    void shouldThrowExceptionWhenPasswordIsInWrongFormat(String password, String exceptionMessage) {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                        confirmationCodeRepository, publisher);

        //When
        Exception exception =
                assertThrows(WrongPasswordFormatException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                .firstName(FIRST_NAME)
                                .lastName(LAST_NAME)
                                .mail(USER_EMAIL)
                                .phoneNumber(PHONE_NUMBER)
                                .password(password)
                                .accountType(AccountType.CUSTOMER)
                                .build()));

        //Then
        assertEquals(exceptionMessage, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserAccountIsNull() {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                        confirmationCodeRepository, publisher);

        //When
        Exception exception =
                assertThrows(ApiRequestException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                .firstName(FIRST_NAME)
                                .lastName(LAST_NAME)
                                .mail(USER_EMAIL)
                                .phoneNumber(PHONE_NUMBER)
                                .password(USER_PASSWORD)
                                .accountType(null)
                                .build()));

        //Then
        assertEquals("Missing accountType argument.", exception.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void shouldThrowExceptionWhenPhoneNumberIsNullOrEmpty(String phoneNumber) {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                        confirmationCodeRepository, publisher);

        //When
        Exception exception =
                assertThrows(ApiRequestException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                .firstName(FIRST_NAME)
                                .lastName(LAST_NAME)
                                .mail(USER_EMAIL)
                                .phoneNumber(phoneNumber)
                                .password(USER_PASSWORD)
                                .accountType(AccountType.CUSTOMER)
                                .build()));

        //Then
        assertEquals("Invalid phone value.", exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowExceptionWhenFirstNameIsNullOrEmpty(String firstName) {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                        confirmationCodeRepository, publisher);

        //When
        Exception exception =
                assertThrows(ApiRequestException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                .firstName(firstName)
                                .lastName(LAST_NAME)
                                .mail(USER_EMAIL)
                                .phoneNumber(PHONE_NUMBER)
                                .password(USER_PASSWORD)
                                .accountType(AccountType.CUSTOMER)
                                .build()));

        //Then
        assertEquals("Missing firstName value.", exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowExceptionWhenLastNameIsNullOrEmpty(String lastName) {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                        confirmationCodeRepository, publisher);

        //When
        Exception exception =
                assertThrows(ApiRequestException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                .firstName(FIRST_NAME)
                                .lastName(lastName)
                                .mail(USER_EMAIL)
                                .phoneNumber(PHONE_NUMBER)
                                .password(USER_PASSWORD)
                                .accountType(AccountType.CUSTOMER)
                                .build()));

        //Then
        assertEquals("Missing lastName value.", exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowExceptionWhenCompanyNameIsNullOrEmpty(String companyName) {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                        confirmationCodeRepository, publisher);

        //When
        Exception exception =
                assertThrows(ApiRequestException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                .firstName(FIRST_NAME)
                                .lastName(LAST_NAME)
                                .mail(USER_EMAIL)
                                .phoneNumber(PHONE_NUMBER)
                                .password(USER_PASSWORD)
                                .accountType(AccountType.COMPANY)
                                .companyName(companyName)
                                .build()));

        //Then
        assertEquals("Invalid companyName value.", exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowExceptionWhenNipIsNullOrEmpty(String nip) {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                        confirmationCodeRepository, publisher);

        //When
        Exception exception =
                assertThrows(ApiRequestException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                .firstName(FIRST_NAME)
                                .lastName(LAST_NAME)
                                .mail(USER_EMAIL)
                                .phoneNumber(PHONE_NUMBER)
                                .password(USER_PASSWORD)
                                .accountType(AccountType.COMPANY)
                                .companyName(COMPANY_NAME)
                                .nip(nip)
                                .build()));

        //Then
        assertEquals("Invalid NIP value.", exception.getMessage());
    }

    public static Stream<Arguments> providePasswords() {
        return Stream.of(
                Arguments.of("abc", "Password should be at least 8 characters long"),
                Arguments.of(null, "Password should be at least 8 characters long"),
                Arguments.of("Password*", "Password should contains at least one digit"),
                Arguments.of("password123*", "Password should contains at least one uppercase letter"),
                Arguments.of("PASSWORD123*", "Password should be at least one lowercase letter"),
                Arguments.of("Password123", "Password should contains at least one special character")
        );
    }

    public static Stream<Arguments> provideWrongEmailAddresses() {
        return Stream.of(
                Arguments.of("abc.example.com"),
                Arguments.of("a@b@c@example.com"),
                Arguments.of("a\"b(c)d,e:f;g<h>i[j\\k]l@example.com"),
                Arguments.of("just\"not\"right@example.com"),
                Arguments.of("\"not\\allowed@example.com"),
                Arguments.of("i.like.underscores@but_they_are_not_allowed_in_this_part"),
                Arguments.of("1234567890123456789012345678901234567890123456789012345678901234+x@example.com")
        );
    }
}