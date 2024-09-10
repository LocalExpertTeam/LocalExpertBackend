package com.local_expert.backend.account_management.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userAccountRegistrationAccountRepository")
interface UserAccountRegistrationAccountRepository extends JpaRepository<UserAccountRegistrationAccountEntity, Long> {

    Optional<UserAccountRegistrationAccountEntity> findByMail(String mail);
}
