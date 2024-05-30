package com.example.LocalExpertBackend.user.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserAccountAuthenticationAccountRepository")
interface UserAuthenticationAccountRepository extends JpaRepository<UserAuthenticationAccountEntity, Long> {

    Optional<UserAuthenticationAccountEntity> findByMail(String mail);
}
