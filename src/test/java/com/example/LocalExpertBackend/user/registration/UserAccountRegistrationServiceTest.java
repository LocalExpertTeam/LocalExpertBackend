package com.example.LocalExpertBackend.user.registration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
    @Mock
    private UserAccountRegistrationAccountRepository repository;
    @Mock
    private UserAccountRegistrationCompanyRepository companyRepository;
    @Mock
    private UserAccountRegistrationCustomerRepository customerRepository;

    @Test
    void shouldSaveNewUserAccount() {
        //Given
        UserAccountRegistrationService service = new UserAccountRegistrationService(repository, customerRepository, companyRepository);
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
    }

    @Test
    void shouldThrowExceptionWhenEmailIsAlreadyInUse() {
        //Given
        UserAccountRegistrationService service = new UserAccountRegistrationService(repository, customerRepository, companyRepository);
        when(repository.findByMail(anyString())).thenReturn(Optional.of(new UserAccountRegistrationAccountEntity()));

        //When
        Exception exception =
                assertThrows(UserAlreadyExistsException.class,
                        () -> service.createNewUserAccount(UserDAO.builder()
                                                                .firstName(FIRST_NAME)
                                                                .lastName(LAST_NAME)
                                                                .mail(USER_EMAIL)
                                                                .phoneNumber(PHONE_NUMBER)
                                                                .password(USER_PASSWORD)
                                                                .accountType(AccountType.CUSTOMER)
                                                                .build()));

        //Then
        assertEquals("Account with email: " + USER_EMAIL + " already exists.", exception.getMessage());
    }
}