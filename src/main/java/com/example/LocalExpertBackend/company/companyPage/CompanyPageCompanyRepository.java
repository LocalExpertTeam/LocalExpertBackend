package com.example.LocalExpertBackend.company.companyPage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("CompanyPageCompanyRepository")
public interface CompanyPageCompanyRepository extends JpaRepository<CompanyPageCompanyEntity, Long> {
    @Override
    Optional<CompanyPageCompanyEntity> findById(Long id);
}
