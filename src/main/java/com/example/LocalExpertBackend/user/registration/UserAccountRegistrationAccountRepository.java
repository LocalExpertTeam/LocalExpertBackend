package com.example.LocalExpertBackend.user.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserAccountRegistrationAccountRepository")
interface UserAccountRegistrationAccountRepository extends JpaRepository<UserAccountRegistrationAccountEntity, Long> {

    Optional<UserAccountRegistrationAccountEntity> findByMail(String mail);
}
