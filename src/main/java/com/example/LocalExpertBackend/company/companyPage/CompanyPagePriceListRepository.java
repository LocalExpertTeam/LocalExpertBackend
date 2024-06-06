package com.example.LocalExpertBackend.company.companyPage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CompanyPagePriceListRepository")
public interface CompanyPagePriceListRepository extends JpaRepository<CompanyPagePriceListEntity, Long> {
    List<CompanyPagePriceListEntity> getByCompanyId(Long companyId);
}
