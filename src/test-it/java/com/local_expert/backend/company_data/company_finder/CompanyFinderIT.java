package com.local_expert.backend.company_data.company_finder;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CompanyFinderIT {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest");

    @Autowired
    private CompanyFinderServiceRepository serviceRepository;
    @Autowired
    private CompanyFinderCityRepository companyFinderCityRepository;
    @Autowired
    private CompanyFinderScopeRepository companyFinderScopeRepository;

    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        mySQLContainer.stop();
    }

    @Test
    @Sql("/db-scripts/company_data/company_finder/company-finder-controller-scope-it.sql")
    @Transactional
    void doesGetScopeFromDB() {
        CompanyFinderController companyFinderController = new CompanyFinderController(serviceRepository, companyFinderCityRepository, companyFinderScopeRepository);
        ResponseEntity<?> re = companyFinderController.getAll();
        assertTrue(re.hasBody());
    }

    @Test
    @Sql("/db-scripts/company_data/company_finder/company-finder-controller-scope-it.sql")
    @Transactional
    void doesOrderScopeAsc() {
        CompanyFinderController companyFinderController = new CompanyFinderController(serviceRepository, companyFinderCityRepository, companyFinderScopeRepository);
        companyFinderController.getAll();
        assertTrue(companyFinderController.getCompanyFinderObject().getScope().get(1).getValue() > companyFinderController.getCompanyFinderObject().getScope().get(0).getValue());
    }

    @Test
    @Sql(scripts = "/db-scripts/company_data/company_finder/company-finder-controller-province-it.sql", config = @SqlConfig(commentPrefix = "`"))
    @Sql("/db-scripts/company_data/company_finder/company-finder-controller-city-it.sql")
    @Transactional
    void doesGetCityFromDB() {
        CompanyFinderController companyFinderController = new CompanyFinderController(serviceRepository, companyFinderCityRepository, companyFinderScopeRepository);
        ResponseEntity<?> re = companyFinderController.getAll();
        assertTrue(re.hasBody());
    }

    @Test
    @Sql(scripts = "/db-scripts/company_data/company_finder/company-finder-controller-province-it.sql", config = @SqlConfig(commentPrefix = "`"))
    @Sql("/db-scripts/company_data/company_finder/company-finder-controller-city-it.sql")
    @Transactional
    void doesOrderCityAsc() {
        CompanyFinderController companyFinderController = new CompanyFinderController(serviceRepository, companyFinderCityRepository, companyFinderScopeRepository);
        companyFinderController.getAll();
        int diff = companyFinderController.getCompanyFinderObject().getCity().get(1).getValue().compareTo(companyFinderController.getCompanyFinderObject().getCity().get(0).getValue());
        assertTrue(diff > 0);
    }

    @Test
    @Sql("/db-scripts/company_data/company_finder/company-finder-controller-service-it.sql")
    @Transactional
    void doesGetServiceFromDB() {
        CompanyFinderController companyFinderController = new CompanyFinderController(serviceRepository, companyFinderCityRepository, companyFinderScopeRepository);
        ResponseEntity<?> re = companyFinderController.getAll();
        assertTrue(re.hasBody());
    }

    @Test
    @Sql("/db-scripts/company_data/company_finder/company-finder-controller-service-it.sql")
    @Transactional
    void doesOrderServiceAsc() {
        CompanyFinderController companyFinderController = new CompanyFinderController(serviceRepository, companyFinderCityRepository, companyFinderScopeRepository);
        companyFinderController.getAll();
        int diff = companyFinderController.getCompanyFinderObject().getService().get(1).getName().compareTo(companyFinderController.getCompanyFinderObject().getService().get(0).getName());
        assertTrue(diff > 0);
    }

}
