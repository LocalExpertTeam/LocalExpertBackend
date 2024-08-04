package com.example.LocalExpertBackend.visitor.company_list;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class VisitorCompanyListServiceIT {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest");

    public static final String CITY_NS = "Nowa Sól";
    public static final String CITY_ZG = "Zielona Góra";
    public static final String CITY_S = "Sulechów";
    public static final int SCOPE_100 = 100;
    public static final String SERVICE = "Deratyzacja";
    public static final int PAGE_SIZE = 10;
    public static final int PAGE_NUMBER = 0;
    public static final String RATE_SORT_PARAMETER = "rate";
    public static final String DATE_SORT_PARAMETER = "creation_date";
    public static final String NAME_SORT_PARAMETER = "name";

    @Autowired
    private VisitorCompanyListCompanyRepository repository;

    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        mySQLContainer.stop();
    }

    @Test
    @Sql("/db-scripts/visitor/company_list/visitor-company-list-service-it.sql")
    @Transactional
    void shouldReturnCompanyListPageWithProperRate() {
        //Given
        VisitorCompanyListService companyListService = new VisitorCompanyListService(repository);

        //When
        CompanyListPage companyListPage =
                companyListService.getCompanyListPage(CITY_NS, SCOPE_100, SERVICE, PAGE_SIZE, PAGE_NUMBER,
                                                        Sort.Direction.DESC, RATE_SORT_PARAMETER);

        //Then
        assertEquals(4.0, companyListPage.getCompanies().get(0).getMeanRate());
        assertEquals(2, companyListPage.getCompanies().get(0).getRateCount());
    }

    @Test
    @Sql("/db-scripts/visitor/company_list/visitor-company-list-service-it.sql")
    @Transactional
    void shouldReturnCompanyListPageSortedByNameDESC() {
        //Given
        VisitorCompanyListService companyListService = new VisitorCompanyListService(repository);

        //When
        CompanyListPage companyListPage =
                companyListService.getCompanyListPage(CITY_NS, 10, SERVICE, PAGE_SIZE, PAGE_NUMBER,
                        Sort.Direction.DESC, NAME_SORT_PARAMETER);

        //Then
        assertEquals("Wołek sp. z.ł.o", companyListPage.getCompanies().get(0).getName());
        assertEquals("STL", companyListPage.getCompanies().get(1).getName());
    }

    @Test
    @Sql("/db-scripts/visitor/company_list/visitor-company-list-service-it.sql")
    @Transactional
    void shouldReturnCompanyListPageSortedByAddedDateASC() {
        //Given
        VisitorCompanyListService companyListService = new VisitorCompanyListService(repository);

        //When
        CompanyListPage companyListPage =
                companyListService.getCompanyListPage(CITY_NS, 10, SERVICE, PAGE_SIZE, PAGE_NUMBER,
                        Sort.Direction.ASC, DATE_SORT_PARAMETER);

        //Then
        assertEquals("Wołek sp. z.ł.o", companyListPage.getCompanies().get(0).getName());
        assertEquals("STL", companyListPage.getCompanies().get(1).getName());
    }

    @Test
    @Sql("/db-scripts/visitor/company_list/visitor-company-list-service-it.sql")
    @Transactional
    void shouldReturnCompanyListPageWithoutRates() {
        //Given
        VisitorCompanyListService companyListService = new VisitorCompanyListService(repository);

        //When
        CompanyListPage companyListPage =
                companyListService.getCompanyListPage(CITY_S, SCOPE_100, null, PAGE_SIZE, PAGE_NUMBER,
                        Sort.Direction.DESC, DATE_SORT_PARAMETER);

        //Then
        assertEquals("PKP", companyListPage.getCompanies().get(0).getName());
        assertEquals(0.0, companyListPage.getCompanies().get(0).getMeanRate());
        assertEquals(0, companyListPage.getCompanies().get(0).getRateCount());
    }

    @Test
    @Sql("/db-scripts/visitor/company_list/visitor-company-list-service-it.sql")
    @Transactional
    void shouldReturnCompanyListPageWithoutCompanies() {
        //Given
        VisitorCompanyListService companyListService = new VisitorCompanyListService(repository);

        //When
        CompanyListPage companyListPage =
                companyListService.getCompanyListPage(CITY_ZG, SCOPE_100, "Fake service", PAGE_SIZE, PAGE_NUMBER,
                        Sort.Direction.DESC, DATE_SORT_PARAMETER);

        //Then
        assertEquals(Collections.EMPTY_LIST, companyListPage.getCompanies());
    }

    @Test
    @Sql("/db-scripts/visitor/company_list/visitor-company-list-service-it.sql")
    @Transactional
    void shouldReturnProperPageParameters() {
        //Given
        VisitorCompanyListService companyListService = new VisitorCompanyListService(repository);

        //When
        CompanyListPage companyListPage =
                companyListService.getCompanyListPage(CITY_NS, 10, SERVICE, PAGE_SIZE, PAGE_NUMBER,
                        Sort.Direction.DESC, DATE_SORT_PARAMETER);

        //Then
        assertEquals(2, companyListPage.getTotalItems());
        assertEquals(1, companyListPage.getTotalPages());
        assertEquals(0, companyListPage.getCurrentPage());
        assertEquals(10, companyListPage.getPageSize());
    }

    @Test
    @Sql("/db-scripts/visitor/company_list/visitor-company-list-service-it.sql")
    @Transactional
    void shouldReturnCompanyListPageWithProperCompanyParameters() {
        //Given
        VisitorCompanyListService companyListService = new VisitorCompanyListService(repository);

        //When
        CompanyListPage companyListPage =
                companyListService.getCompanyListPage(CITY_S, SCOPE_100, null, PAGE_SIZE, PAGE_NUMBER,
                        Sort.Direction.DESC, DATE_SORT_PARAMETER);

        //Then
        assertEquals(3, companyListPage.getCompanies().get(0).getId());
        assertEquals("/profile", companyListPage.getCompanies().get(0).getProfile());
        assertEquals("We are an awesome company", companyListPage.getCompanies().get(0).getDescription());
        assertEquals("PKP", companyListPage.getCompanies().get(0).getName());
        assertEquals(0.0, companyListPage.getCompanies().get(0).getMeanRate());
        assertEquals(0, companyListPage.getCompanies().get(0).getRateCount());
    }
}