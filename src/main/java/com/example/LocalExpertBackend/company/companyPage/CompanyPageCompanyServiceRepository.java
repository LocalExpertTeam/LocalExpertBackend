package com.example.LocalExpertBackend.company.companyPage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CompanyPageCompanyServiceRepository")
public interface CompanyPageCompanyServiceRepository extends JpaRepository<CompanyPageCompanyServiceEntity, Long> {
    List<CompanyPageCompanyServiceEntity> findByCompanyId(Long id);
}
