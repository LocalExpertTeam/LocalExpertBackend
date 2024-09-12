package com.local_expert.backend.company_data.company_list;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("visitorCompanyListCompanyRepository")
interface VisitorCompanyListCompanyRepository extends PagingAndSortingRepository<VisitorCompanyListCompanyEntity, Long> {

    Page<VisitorCompanyListCompanyEntity>
    findAllByRegionsCityCityAndContractsServiceNameAndRegionsScopeScopeGreaterThanEqual(String city, String service,
                                                                                        int scope, Pageable pageable);
}
