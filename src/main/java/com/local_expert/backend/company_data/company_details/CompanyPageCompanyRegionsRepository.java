package com.local_expert.backend.company_data.company_details;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("companyPageCompanyRegionsRepository")
public interface CompanyPageCompanyRegionsRepository extends JpaRepository<CompanyPageCompanyRegionEntity, Long> {
    List<CompanyPageCompanyRegionEntity> findByCompanyId(Long id);
}
