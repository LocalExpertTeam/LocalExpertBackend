package com.local_expert.backend.account_management.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userAccountRegistrationCustomerRepository")
interface UserAccountRegistrationCustomerRepository extends JpaRepository<UserAccountRegistrationCustomerEntity, Long> {

}
