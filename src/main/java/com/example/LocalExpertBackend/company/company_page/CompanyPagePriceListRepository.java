package com.example.LocalExpertBackend.company.company_page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("companyPagePriceListRepository")
public interface CompanyPagePriceListRepository extends JpaRepository<CompanyPagePriceListEntity, Long> {
    List<CompanyPagePriceListEntity> getByCompanyId(Long companyId);
}
