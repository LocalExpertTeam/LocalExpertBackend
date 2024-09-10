package com.local_expert.backend.company_data.company_page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("companyPageCompanyServiceRepository")
public interface CompanyPageCompanyServiceRepository extends JpaRepository<CompanyPageCompanyServiceEntity, Long> {
    List<CompanyPageCompanyServiceEntity> findByCompanyId(Long id);
}
