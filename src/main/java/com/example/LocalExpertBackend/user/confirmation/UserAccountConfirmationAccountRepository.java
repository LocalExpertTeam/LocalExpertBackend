package com.example.LocalExpertBackend.user.confirmation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserAccountConfirmationAccountRepository")
interface UserAccountConfirmationAccountRepository extends JpaRepository<UserAccountConfirmationAccountEntity, Long> {

}
