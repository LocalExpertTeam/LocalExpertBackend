package com.example.LocalExpertBackend.companyFinder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CompanyFinderScopeRepository")
interface CompanyFinderScopeRepository extends JpaRepository<ScopeEntity, Long> {
    List<ScopeEntity> findAllByOrderByValueAsc();
}
