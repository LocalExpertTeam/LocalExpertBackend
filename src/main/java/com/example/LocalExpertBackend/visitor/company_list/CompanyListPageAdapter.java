package com.example.LocalExpertBackend.visitor.company_list;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CompanyListPageAdapter {

    private Page<VisitorCompanyListCompanyEntity> companyPage;

    public CompanyListPage getCompanyListPage() {

        List<Company> companies = new ArrayList<>();

        for (VisitorCompanyListCompanyEntity company : companyPage.getContent()) {
            companies.add(prepareCompany(company));
        }

        return CompanyListPage.builder()
                                .totalPages(companyPage.getTotalPages())
                                .currentPage(companyPage.getNumber())
                                .pageSize(companyPage.getSize())
                                .companies(companies)
                                .totalItems(companyPage.getTotalElements())
                                .build();
    }

    private Company prepareCompany(VisitorCompanyListCompanyEntity company) {
        double meanRate = 0;
        long rateCount = 0;
        for (VisitorCompanyListContractEntity contract : company.getContracts()) {
            if (contract.getComment() != null) {
                meanRate += contract.getComment()
                                    .getRate();
                rateCount++;
            }
        }

        if (rateCount != 0) {
            meanRate /= rateCount;
        }

        return Company.builder()
                    .id(company.getId())
                    .profile(company.getProfile())
                    .description(company.getDescription())
                    .meanRate(meanRate)
                    .name(company.getName())
                    .rateCount(rateCount)
                    .build();
    }
}
