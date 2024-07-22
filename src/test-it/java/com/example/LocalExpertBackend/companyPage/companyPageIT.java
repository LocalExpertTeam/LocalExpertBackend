package com.example.LocalExpertBackend.companyPage;

import com.example.LocalExpertBackend.company.companyPage.*;
import com.example.LocalExpertBackend.companyFinder.CompanyFinderController;
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
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class companyPageIT {
    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest");

    @Autowired
    private CompanyPageCompanyRepository companyPageCompanyRepository;
    @Autowired
    private CompanyPageCompanyRegionsRepository companyPageCompanyRegionsRepository;
    @Autowired
    private CompanyPagePriceListRepository companyPagePriceListRepository;
    @Autowired
    private CompanyPageCompanyServiceRepository companyPageCompanyServiceRepository;

    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        mySQLContainer.stop();
    }

    @Test
    @Transactional
    void doesFindUnfoundable() {
        CompanyPageController companyPageController = new CompanyPageController(companyPageCompanyRepository, companyPageCompanyRegionsRepository, companyPagePriceListRepository, companyPageCompanyServiceRepository);
        ResponseEntity<?> re = companyPageController.getById(123232L);
        assertThat(re.getStatusCode());
    }
    @Test
    @Sql("/db-scripts.company-page/companyPage-it.sql")
    @Transactional
    void doesGetServiceFromDB() {
        CompanyPageController companyPageController = new CompanyPageController(companyPageCompanyRepository, companyPageCompanyRegionsRepository, companyPagePriceListRepository, companyPageCompanyServiceRepository);
        ResponseEntity<?> re = companyPageController.getById(1L);
        assertThat(companyPageController.getCompanyPageObject().getCompany().equals("S&P Global"));
    }
}
