package com.local_expert.backend.account_management.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userAccountAuthenticationAccountRepository")
interface UserAuthenticationAccountRepository extends JpaRepository<UserAuthenticationAccountEntity, Long> {

    Optional<UserAuthenticationAccountEntity> findByMail(String mail);
}
