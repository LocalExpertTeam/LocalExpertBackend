package com.example.LocalExpertBackend.user.confirmation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserAccountConfirmationConfirmationCodeRepository")
interface UserAccountConfirmationConfirmationCodeRepository
        extends JpaRepository<UserAccountConfirmationConfirmationCodeEntity, Long> {

    Optional<UserAccountConfirmationConfirmationCodeEntity> findByUserMail(String mail);
}
