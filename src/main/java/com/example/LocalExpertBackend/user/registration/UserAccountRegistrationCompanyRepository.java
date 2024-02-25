package com.example.LocalExpertBackend.user.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserAccountRegistrationCompanyRepository")
interface UserAccountRegistrationCompanyRepository extends JpaRepository<UserAccountRegistrationCompanyEntity, Long> {

}
