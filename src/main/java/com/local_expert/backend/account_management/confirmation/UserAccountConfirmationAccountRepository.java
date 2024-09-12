package com.local_expert.backend.account_management.confirmation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userAccountConfirmationAccountRepository")
interface UserAccountConfirmationAccountRepository extends JpaRepository<UserAccountConfirmationAccountEntity, Long> {

}
