package com.local_expert.backend.account_management.confirmation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userAccountConfirmationConfirmationCodeRepository")
interface UserAccountConfirmationConfirmationCodeRepository
        extends JpaRepository<UserAccountConfirmationConfirmationCodeEntity, Long> {

    Optional<UserAccountConfirmationConfirmationCodeEntity> findByUserMail(String mail);
}
