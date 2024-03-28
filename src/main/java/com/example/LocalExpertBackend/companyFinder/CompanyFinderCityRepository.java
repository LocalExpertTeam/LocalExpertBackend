package com.example.LocalExpertBackend.companyFinder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CompanyFinderCityRepository")
interface CompanyFinderCityRepository extends JpaRepository<CityEntity, Long> {
    List<CityEntity> findAllByOrderByValueAsc();
}
