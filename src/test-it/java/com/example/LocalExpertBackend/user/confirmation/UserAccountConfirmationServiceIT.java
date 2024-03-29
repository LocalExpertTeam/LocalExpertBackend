package com.example.LocalExpertBackend.user.confirmation;

import com.example.LocalExpertBackend.exception.ApiRequestException;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserAccountConfirmationServiceIT {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest");

    public static final String CONFIRMATION_CODE = "0436";
    public static final String WRONG_CONFIRMATION_CODE = "0134";
    public static final String NOT_EXISTING_ACCOUNT_EMAIL = "paul@gmail.com";
    public static final String EXISTING_ACCOUNT_EMAIL = "mat@gmail.com";
    private static final long ACCOUNT_ID = 1L;

    @Autowired
    private UserAccountConfirmationConfirmationCodeRepository confirmationCodeRepository;
    @Autowired
    private UserAccountConfirmationAccountRepository accountRepository;

    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        mySQLContainer.stop();
    }

    @Test
    @Sql("/db-scripts/user/confirmation/user-confirmation-service-it.sql")
    @Transactional
    void shouldConfirmAccountAndRemoveCode() {
        //Given
        UserAccountConfirmationService service =
                new UserAccountConfirmationService(confirmationCodeRepository, accountRepository);

        //When
        service.confirmUserAccount(UserAccountConfirmationDAO.builder()
                                                            .confirmationCode(CONFIRMATION_CODE)
                                                            .mail(EXISTING_ACCOUNT_EMAIL)
                                                            .build());

        //Then
        UserAccountConfirmationAccountEntity user = accountRepository.getReferenceById(ACCOUNT_ID);
        assertFalse(confirmationCodeRepository.findByUserMail(EXISTING_ACCOUNT_EMAIL).isPresent());
        assertTrue(user.isActive());
        assertEquals(EXISTING_ACCOUNT_EMAIL, user.getMail());
    }

    @Test
    @Sql("/db-scripts/user/confirmation/user-confirmation-service-it.sql")
    @Transactional
    void shouldThrowApiRequestExceptionWhenCodeDoesNotExist() {
        //Given
        UserAccountConfirmationService service =
                new UserAccountConfirmationService(confirmationCodeRepository, accountRepository);

        //When
        ApiRequestException exception = assertThrows(ApiRequestException.class,
                () -> service.confirmUserAccount(UserAccountConfirmationDAO.builder()
                                                                            .confirmationCode(CONFIRMATION_CODE)
                                                                            .mail(NOT_EXISTING_ACCOUNT_EMAIL)
                                                                            .build()));

        //Then
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatusCode());
        assertEquals("Bad or expired code.", exception.getMessage());
    }

    @Test
    @Sql("/db-scripts/user/confirmation/user-confirmation-service-it.sql")
    @Transactional
    void shouldThrowApiRequestExceptionWhenCodeIsWrong() {
        //Given
        UserAccountConfirmationService service =
                new UserAccountConfirmationService(confirmationCodeRepository, accountRepository);

        //When
        ApiRequestException exception = assertThrows(ApiRequestException.class,
                () -> service.confirmUserAccount(UserAccountConfirmationDAO.builder()
                        .confirmationCode(WRONG_CONFIRMATION_CODE)
                        .mail(NOT_EXISTING_ACCOUNT_EMAIL)
                        .build()));

        //Then
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatusCode());
        assertEquals("Bad or expired code.", exception.getMessage());
    }
}