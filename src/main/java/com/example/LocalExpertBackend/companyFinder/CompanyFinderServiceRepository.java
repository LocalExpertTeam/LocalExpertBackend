package com.example.LocalExpertBackend.companyFinder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CompanyFinderServiceRepository")
interface CompanyFinderServiceRepository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceEntity> findAllByOrderByNameAsc();
}
