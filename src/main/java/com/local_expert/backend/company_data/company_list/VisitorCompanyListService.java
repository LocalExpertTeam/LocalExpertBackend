package com.local_expert.backend.company_data.company_list;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VisitorCompanyListService {

    private VisitorCompanyListCompanyRepository repository;

    public CompanyListPage getCompanyListPage(String city, int scope, String service, int pageSize,
                                              int pageNumber, Sort.Direction sortDirection, String sortParameter) {
        String validatedSortParameter = switch (sortParameter) {
            case "name" -> "name";
            case "creation_date" -> "creationDate";

            default -> "contracts_comment_rate";
        };

        Pageable page = PageRequest.of(pageNumber, pageSize, sortDirection, validatedSortParameter);
        Page<VisitorCompanyListCompanyEntity> companies =
                repository.findAllByRegionsCityCityAndContractsServiceNameAndRegionsScopeScopeGreaterThanEqual(city, service, scope, page);

        CompanyListPageAdapter companyListPageAdapter = new CompanyListPageAdapter(companies);
        return companyListPageAdapter.getCompanyListPage();
    }
}
