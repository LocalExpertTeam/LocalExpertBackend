package com.example.LocalExpertBackend.user.registration;

import com.example.LocalExpertBackend.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
class UserAccountRegistrationService {

    private UserAccountRegistrationAccountRepository repository;
    private UserAccountRegistrationCustomerRepository customerRepository;
    private UserAccountRegistrationCompanyRepository companyRepository;

    public void createNewUserAccount(UserDAO user) {

        validateAccountData(user);

        if (repository.findByMail(user.getMail()).isPresent()) {
            throw new UserAlreadyExistsException("Account with email: " + user.getMail() + " already exists.");
        }

        UserAccountRegistrationAccountEntity accountEntity = UserAccountRegistrationAccountEntity.valueOfUserDAO(user);
        repository.save(accountEntity);
        if (user.getAccountType().equals(AccountType.CUSTOMER)) {
            UserAccountRegistrationCustomerEntity entity = UserAccountRegistrationCustomerEntity.valueOfUserDAO(user);
            entity.setAccount(accountEntity);
            customerRepository.save(entity);
        } else {

            validateCompanyData(user);

            UserAccountRegistrationCompanyEntity entity = UserAccountRegistrationCompanyEntity.valueOfUserDAO(user);
            entity.setAccount(accountEntity);
            companyRepository.save(entity);
        }
    }

    private void validateAccountData(UserDAO user) {
        if (!EmailValidator.isInEmailFormat(user.getMail())) {
            throw new WrongEmailFormatException("Email address has given in wrong format.");
        }

        String password = user.getPassword();

        if (password == null || password.length() < 8) {
            throw new WrongPasswordFormatException("Password should be at least 8 characters long");
        } else if (!PasswordValidator.containsAtLeastOneDigit(password)) {
            throw new WrongPasswordFormatException("Password should contains at least one digit");
        } else if (!PasswordValidator.containsAtLeastOneUppercaseLetter(password)) {
            throw new WrongPasswordFormatException("Password should contains at least one uppercase letter");
        } else if (!PasswordValidator.containsAtLeastOneLowercaseLetter(password)) {
            throw new WrongPasswordFormatException("Password should be at least one lowercase letter");
        } else if (!PasswordValidator.containsAtLeastOneSpecialCharacter(password)) {
            throw new WrongPasswordFormatException("Password should contains at least one special character");
        }

        if (user.getAccountType() == null) {
            throw new ApiRequestException("Missing accountType argument.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (user.getPhoneNumber() != null && user.getPhoneNumber().length() != 9) {
            throw new ApiRequestException("Invalid phone value.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (user.getFirstName() == null || user.getFirstName().isBlank()) {
            throw new ApiRequestException("Missing firstName value.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (user.getLastName() == null || user.getLastName().isBlank()) {
            throw new ApiRequestException("Missing lastName value.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private void validateCompanyData(UserDAO user) {
        if (user.getCompanyName() == null || user.getCompanyName().isBlank()) {
            throw new ApiRequestException("Invalid companyName value.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (user.getNip() == null || user.getNip().length() != 10) {
            throw new ApiRequestException("Invalid NIP value.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
