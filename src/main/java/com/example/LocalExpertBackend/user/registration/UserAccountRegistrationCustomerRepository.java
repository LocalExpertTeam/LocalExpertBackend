package com.example.LocalExpertBackend.user.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserAccountRegistrationCustomerRepository")
interface UserAccountRegistrationCustomerRepository extends JpaRepository<UserAccountRegistrationCustomerEntity, Long> {

}
