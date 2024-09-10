package com.example.LocalExpertBackend.company.company_page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("companyPageCompanyRepository")
public interface CompanyPageCompanyRepository extends JpaRepository<CompanyPageCompanyEntity, Long> {

}
