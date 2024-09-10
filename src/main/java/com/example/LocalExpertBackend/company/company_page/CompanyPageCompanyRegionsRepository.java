package com.example.LocalExpertBackend.company.company_page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("companyPageCompanyRegionsRepository")
public interface CompanyPageCompanyRegionsRepository extends JpaRepository<CompanyPageCompanyRegionEntity, Long> {
    List<CompanyPageCompanyRegionEntity> findByCompanyId(Long id);
}
