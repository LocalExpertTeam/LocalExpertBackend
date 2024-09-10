package com.local_expert.backend.company_data.company_finder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("companyFinderServiceRepository")
interface CompanyFinderServiceRepository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceEntity> findAllByOrderByNameAsc();
}
