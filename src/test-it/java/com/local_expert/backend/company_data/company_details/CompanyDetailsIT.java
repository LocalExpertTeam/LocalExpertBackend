package com.local_expert.backend.company_data.company_details;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CompanyDetailsIT {
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
        assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }

    @Test
    @Sql("/db-scripts/company_data/company_details/company-details-it.sql")
    @Transactional
    void doesGetServiceFromDB() {
        //Given
        CompanyPageController companyPageController = new CompanyPageController(companyPageCompanyRepository, companyPageCompanyRegionsRepository, companyPagePriceListRepository, companyPageCompanyServiceRepository);

        //When
        companyPageController.getById(1L);

        //Then
        assertEquals("S&P Global", companyPageController.getCompanyPageObject().getCompany().getName());

        assertEquals("Dekarstwo", companyPageController.getCompanyPageObject().getServices().get(0));
        assertEquals("Malowanie", companyPageController.getCompanyPageObject().getServices().get(1));

        assertEquals("Sulechów, Lubuskie", companyPageController.getCompanyPageObject().getRegions().get(0).getCity());

        assertEquals("A", companyPageController.getCompanyPageObject().getPriceList().get(0).getServiceName());
        assertEquals("B", companyPageController.getCompanyPageObject().getPriceList().get(1).getServiceName());
    }
}
