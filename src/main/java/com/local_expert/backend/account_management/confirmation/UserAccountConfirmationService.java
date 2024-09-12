package com.local_expert.backend.account_management.confirmation;

import com.local_expert.backend.exception.ApiRequestException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
class UserAccountConfirmationService {

    private UserAccountConfirmationConfirmationCodeRepository repository;
    private UserAccountConfirmationAccountRepository accountRepository;

    @Transactional
    public void confirmUserAccount(UserAccountConfirmationDAO confirmationDAO) {
        Optional<UserAccountConfirmationConfirmationCodeEntity> confirmationCodeOptionalEntity =
                repository.findByUserMail(confirmationDAO.getMail());
        if (confirmationCodeOptionalEntity.isPresent()) {
            UserAccountConfirmationConfirmationCodeEntity confirmationCodeEntity = confirmationCodeOptionalEntity.get();

            if (confirmationCodeEntity.getConfirmationCode().equals(confirmationDAO.getConfirmationCode())) {
                UserAccountConfirmationAccountEntity accountEntity = confirmationCodeEntity.getUser();
                accountEntity.setActive(true);
                accountRepository.save(accountEntity);
                repository.delete(confirmationCodeEntity);
                return;
            }
        }

        throw new ApiRequestException("Bad or expired code.", HttpStatus.NOT_FOUND);
    }
}
