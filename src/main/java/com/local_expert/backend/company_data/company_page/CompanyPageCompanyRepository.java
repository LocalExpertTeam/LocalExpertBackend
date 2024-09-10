package com.local_expert.backend.company_data.company_page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("companyPageCompanyRepository")
public interface CompanyPageCompanyRepository extends JpaRepository<CompanyPageCompanyEntity, Long> {

}
