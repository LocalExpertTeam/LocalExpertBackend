package com.local_expert.backend.company_data.company_finder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("companyFinderCityRepository")
interface CompanyFinderCityRepository extends JpaRepository<CityEntity, Long> {
    List<CityEntity> findAllByOrderByNameAsc();
}
