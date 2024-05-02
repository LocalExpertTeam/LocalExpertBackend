package com.example.LocalExpertBackend.user.registration;

import com.example.LocalExpertBackend.enums.AccountType;
import com.example.LocalExpertBackend.exception.ApiRequestException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserAccountRegistrationServiceIT {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest");

    public static final String COMPANY_NAME = "Bulldozaur sp. z o.o.";
    public static final String NIP = "1010304866";
    public static final String NEW_USER_EMAIL = "paul@gmail.com";
    public static final String EXISTING_USER_EMAIL = "mat@gmail.com";
    public static final String USER_PASSWORD = "Password123*";
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Cena";
    public static final String PHONE_NUMBER = "664664512";
    public static final String TOO_LONG_PHONE_NUMBER = "664664512123";

    @Autowired
    private UserAccountRegistrationAccountRepository repository;
    @Autowired
    private UserAccountRegistrationCompanyRepository companyRepository;
    @Autowired
    private UserAccountRegistrationCustomerRepository customerRepository;
    @Autowired
    private UserAccountRegistrationConfirmationCodeRepository confirmationCodeRepository;
    @Autowired
    private ApplicationEventPublisher publisher;

    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        mySQLContainer.stop();
    }

    @Test
    @Sql("/db-scripts/user/registration/user-registration-service-it.sql")
    @Transactional
    void shouldSaveNewCustomerAccount() {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                                                    confirmationCodeRepository, publisher);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //When
        service.createNewUserAccount(UserDAO.builder()
                                            .firstName(FIRST_NAME)
                                            .lastName(LAST_NAME)
                                            .mail(NEW_USER_EMAIL)
                                            .phoneNumber(PHONE_NUMBER)
                                            .password(USER_PASSWORD)
                                            .accountType(AccountType.CUSTOMER)
                                            .build());

        //Then
        UserAccountRegistrationAccountEntity createdAccount = repository.findByMail(NEW_USER_EMAIL).get();
        assertEquals(NEW_USER_EMAIL, createdAccount.getMail());
        assertTrue(passwordEncoder.matches(USER_PASSWORD, createdAccount.getPassword()));
        assertEquals(AccountType.CUSTOMER, createdAccount.getAccountType());
        assertEquals(PHONE_NUMBER, createdAccount.getPhoneNumber());
    }

    @Test
    @Sql("/db-scripts/user/registration/user-registration-service-it.sql")
    @Transactional
    void shouldSaveNewCompanyAccount() {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                                                    confirmationCodeRepository, publisher);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //When
        service.createNewUserAccount(UserDAO.builder()
                                            .companyName(COMPANY_NAME)
                                            .nip(NIP)
                                            .firstName(FIRST_NAME)
                                            .lastName(LAST_NAME)
                                            .mail(NEW_USER_EMAIL)
                                            .phoneNumber(PHONE_NUMBER)
                                            .password(USER_PASSWORD)
                                            .accountType(AccountType.COMPANY)
                                            .build());

        //Then
        UserAccountRegistrationAccountEntity createdAccount = repository.findByMail(NEW_USER_EMAIL).get();
        assertEquals(NEW_USER_EMAIL, createdAccount.getMail());
        assertTrue(passwordEncoder.matches(USER_PASSWORD, createdAccount.getPassword()));
        assertEquals(AccountType.COMPANY, createdAccount.getAccountType());
        assertEquals(PHONE_NUMBER, createdAccount.getPhoneNumber());
    }

    @Test
    @Sql("/db-scripts/user/registration/user-registration-service-it.sql")
    @Transactional
    void shouldThrowExceptionWhenEmailIsAlreadyInUse() {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                                                    confirmationCodeRepository, publisher);

        //When
        ApiRequestException exception =
                assertThrows(UserAlreadyExistsException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                                                .firstName(FIRST_NAME)
                                                                .lastName(LAST_NAME)
                                                                .mail(EXISTING_USER_EMAIL)
                                                                .phoneNumber(PHONE_NUMBER)
                                                                .password(USER_PASSWORD)
                                                                .accountType(AccountType.CUSTOMER)
                                                                .build()));

        //Then
        assertEquals("Account with email: " + EXISTING_USER_EMAIL + " already exists.", exception.getMessage());
        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatusCode());
    }

    @ParameterizedTest
    @ValueSource(strings = {"paul@gmail.com.", "@gmail.com", "paulgmail.com", "paulgmailcom"})
    @Sql("/db-scripts/user/registration/user-registration-service-it.sql")
    @Transactional
    void shouldThrowExceptionWhenEmailInWrongFormat(String email) {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                                                    confirmationCodeRepository, publisher);

        //When
        ApiRequestException exception =
                assertThrows(WrongEmailFormatException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                                                .firstName(FIRST_NAME)
                                                                .lastName(LAST_NAME)
                                                                .mail(email)
                                                                .phoneNumber(PHONE_NUMBER)
                                                                .password(USER_PASSWORD)
                                                                .accountType(AccountType.CUSTOMER)
                                                                .build()));

        //Then
        assertEquals("Email address has given in wrong format.", exception.getMessage());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exception.getHttpStatusCode());
    }

    @ParameterizedTest
    @MethodSource("provideWrongPasswords")
    @Sql("/db-scripts/user/registration/user-registration-service-it.sql")
    @Transactional
    void shouldThrowExceptionWhenPasswordInWrongFormat(String password, String exceptionMessage) {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                                                    confirmationCodeRepository, publisher);

        //When
        ApiRequestException exception =
                assertThrows(WrongPasswordFormatException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                                                .firstName(FIRST_NAME)
                                                                .lastName(LAST_NAME)
                                                                .mail(NEW_USER_EMAIL)
                                                                .phoneNumber(PHONE_NUMBER)
                                                                .password(password)
                                                                .accountType(AccountType.CUSTOMER)
                                                                .build()));

        //Then
        assertEquals(exceptionMessage, exception.getMessage());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exception.getHttpStatusCode());
    }

    @Test
    @Sql("/db-scripts/user/registration/user-registration-service-it.sql")
    @Transactional
    void shouldThrowExceptionWhenAccountTypeNotProvided() {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                                                    confirmationCodeRepository, publisher);

        //When
        ApiRequestException exception =
                assertThrows(ApiRequestException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                                                .firstName(FIRST_NAME)
                                                                .lastName(LAST_NAME)
                                                                .mail(NEW_USER_EMAIL)
                                                                .phoneNumber(PHONE_NUMBER)
                                                                .password(USER_PASSWORD)
                                                                .build()));

        //Then
        assertEquals("Missing accountType argument.", exception.getMessage());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exception.getHttpStatusCode());
    }

    @Test
    @Sql("/db-scripts/user/registration/user-registration-service-it.sql")
    @Transactional
    void shouldThrowExceptionWhenUserFirstNameNotProvided() {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                                                    confirmationCodeRepository, publisher);

        //When
        ApiRequestException exception =
                assertThrows(ApiRequestException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                                                .lastName(LAST_NAME)
                                                                .mail(NEW_USER_EMAIL)
                                                                .phoneNumber(PHONE_NUMBER)
                                                                .password(USER_PASSWORD)
                                                                .accountType(AccountType.COMPANY)
                                                                .build()));

        //Then
        assertEquals("Missing firstName value.", exception.getMessage());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exception.getHttpStatusCode());
    }

    @Test
    @Sql("/db-scripts/user/registration/user-registration-service-it.sql")
    @Transactional
    void shouldThrowExceptionWhenUserLastNameNotProvided() {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                                                    confirmationCodeRepository, publisher);

        //When
        ApiRequestException exception =
                assertThrows(ApiRequestException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                                                .firstName(FIRST_NAME)
                                                                .mail(NEW_USER_EMAIL)
                                                                .phoneNumber(PHONE_NUMBER)
                                                                .password(USER_PASSWORD)
                                                                .accountType(AccountType.CUSTOMER)
                                                                .build()));

        //Then
        assertEquals("Missing lastName value.", exception.getMessage());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exception.getHttpStatusCode());
    }

    @Test
    @Sql("/db-scripts/user/registration/user-registration-service-it.sql")
    @Transactional
    void shouldThrowExceptionWhenTooLongPhoneProvided() {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                                                    confirmationCodeRepository, publisher);

        //When
        ApiRequestException exception =
                assertThrows(ApiRequestException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                                                .firstName(FIRST_NAME)
                                                                .lastName(LAST_NAME)
                                                                .mail(NEW_USER_EMAIL)
                                                                .phoneNumber(TOO_LONG_PHONE_NUMBER)
                                                                .password(USER_PASSWORD)
                                                                .accountType(AccountType.CUSTOMER)
                                                                .build()));

        //Then
        assertEquals("Invalid phone value.", exception.getMessage());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exception.getHttpStatusCode());
    }

    @Test
    @Sql("/db-scripts/user/registration/user-registration-service-it.sql")
    @Transactional
    void shouldThrowExceptionWhenCompanyNameNotProvided() {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                                                    confirmationCodeRepository, publisher);

        //When
        ApiRequestException exception =
                assertThrows(ApiRequestException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                                                .firstName(FIRST_NAME)
                                                                .lastName(LAST_NAME)
                                                                .mail(NEW_USER_EMAIL)
                                                                .phoneNumber(PHONE_NUMBER)
                                                                .password(USER_PASSWORD)
                                                                .accountType(AccountType.COMPANY)
                                                                .nip(NIP)
                                                                .build()));

        //Then
        assertEquals("Invalid companyName value.", exception.getMessage());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exception.getHttpStatusCode());
    }

    @Test
    @Sql("/db-scripts/user/registration/user-registration-service-it.sql")
    @Transactional
    void shouldThrowExceptionWhenCompanyNipNotProvided() {
        //Given
        UserAccountRegistrationService service =
                new UserAccountRegistrationService(repository, customerRepository, companyRepository,
                                                    confirmationCodeRepository, publisher);

        //When
        ApiRequestException exception =
                assertThrows(ApiRequestException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                                                .companyName(COMPANY_NAME)
                                                                .firstName(FIRST_NAME)
                                                                .lastName(LAST_NAME)
                                                                .mail(NEW_USER_EMAIL)
                                                                .phoneNumber(PHONE_NUMBER)
                                                                .password(USER_PASSWORD)
                                                                .accountType(AccountType.COMPANY)
                                                                .build()));

        //Then
        assertEquals("Invalid NIP value.", exception.getMessage());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exception.getHttpStatusCode());
    }

    private static Stream<Arguments> provideWrongPasswords() {
        return Stream.of(
                Arguments.of("Pass1*", "Password should be at least 8 characters long"),
                Arguments.of("Password*", "Password should contains at least one digit"),
                Arguments.of("password1*", "Password should contains at least one uppercase letter"),
                Arguments.of("PASSWORD1*", "Password should be at least one lowercase letter"),
                Arguments.of("Password1", "Password should contains at least one special character")
        );
    }
}