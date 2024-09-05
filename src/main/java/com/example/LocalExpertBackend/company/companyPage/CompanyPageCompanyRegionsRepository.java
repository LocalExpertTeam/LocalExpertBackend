package com.example.LocalExpertBackend.company.companyPage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CompanyPageCompanyRegionsRepository")
public interface CompanyPageCompanyRegionsRepository extends JpaRepository<CompanyPageCompanyRegionEntity, Long> {
    List<CompanyPageCompanyRegionEntity> findByCompanyId(Long id);
}
