package com.local_expert.backend.customer_data.customer_details;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("companyCustomerDetailsCustomerRepository")
interface CompanyCustomerDetailsCustomerRepository extends JpaRepository<CompanyCustomerDetailsCustomerEntity, Long> {

    CompanyCustomerDetailsCustomerEntity findById(long customerId);
}
