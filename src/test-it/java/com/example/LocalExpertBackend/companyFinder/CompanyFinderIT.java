package com.example.LocalExpertBackend.companyFinder;

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

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CompanyFinderIT {

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

    private ArrayList<Long> ScopeExp = new ArrayList<Long>();
    private ArrayList<String> CityExp = new ArrayList<String>();
    private ArrayList<String> ServiceExp = new ArrayList<String>();


    @Test
    @Sql("/db-scripts.company-finder/companyFinder-controller-scope-it.sql")
    @Transactional
    void doesGetScopeFromDB() {
        CompanyFinderController companyFinderController = new CompanyFinderController(serviceRepository, companyFinderCityRepository, companyFinderScopeRepository);
        ResponseEntity<?> re = companyFinderController.getAll();
        assertThat(re.hasBody());
    }

    @Test
    @Sql("/db-scripts.company-finder/companyFinder-controller-scope-it.sql")
    @Transactional
    void doesOrderScopeAsc() {
        CompanyFinderController companyFinderController = new CompanyFinderController(serviceRepository, companyFinderCityRepository, companyFinderScopeRepository);
        ResponseEntity<?> re = companyFinderController.getAll();
        assertEquals(true, companyFinderController.getCompanyFinderObject().getScope().get(1) > companyFinderController.getCompanyFinderObject().getScope().get(0));
    }

    @Test
    @Sql(scripts = "/db-scripts.company-finder/companyFinder-controller-province-it.sql", config = @SqlConfig(commentPrefix = "`"))
    @Sql("/db-scripts.company-finder/companyFinder-controller-city-it.sql")
    @Transactional
    void doesGetCityFromDB() {
        CompanyFinderController companyFinderController = new CompanyFinderController(serviceRepository, companyFinderCityRepository, companyFinderScopeRepository);
        ResponseEntity<?> re = companyFinderController.getAll();
        assertThat(re.hasBody());
    }

    @Test
    @Sql(scripts = "/db-scripts.company-finder/companyFinder-controller-province-it.sql", config = @SqlConfig(commentPrefix = "`"))
    @Sql("/db-scripts.company-finder/companyFinder-controller-city-it.sql")
    @Transactional
    void doesOrderCityAsc() {
        CompanyFinderController companyFinderController = new CompanyFinderController(serviceRepository, companyFinderCityRepository, companyFinderScopeRepository);
        ResponseEntity<?> re = companyFinderController.getAll();
        int diff = companyFinderController.getCompanyFinderObject().getCity().get(1).get(0).toString().compareTo(companyFinderController.getCompanyFinderObject().getCity().get(0).get(0).toString());
        assertEquals(true, diff > 0);
    }

    @Test
    @Sql("/db-scripts.company-finder/companyFinder-controller-service-it.sql")
    @Transactional
    void doesGetServiceFromDB() {
        CompanyFinderController companyFinderController = new CompanyFinderController(serviceRepository, companyFinderCityRepository, companyFinderScopeRepository);
        ResponseEntity<?> re = companyFinderController.getAll();
        assertThat(re.hasBody());
    }

    @Test
    @Sql("/db-scripts.company-finder/companyFinder-controller-service-it.sql")
    @Transactional
    void doesOrderServiceAsc() {
        CompanyFinderController companyFinderController = new CompanyFinderController(serviceRepository, companyFinderCityRepository, companyFinderScopeRepository);
        ResponseEntity<?> re = companyFinderController.getAll();
        int diff = companyFinderController.getCompanyFinderObject().getService().get(1).compareTo(companyFinderController.getCompanyFinderObject().getService().get(0));
        assertEquals(true, diff > 0);
    }

}
