package com.example.LocalExpertBackend.visitor.comments_about_customer;

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

import java.sql.Date;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class VisitorCommentsAboutCompanyServiceIT {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest");

    public static final Long ACCOUNT_ID = 1L;
    public static final Long ACCOUNT_WITHOUT_COMMENTS_ID = 2L;
    public static final int PAGE_SIZE = 10;
    public static final int PAGE_NUMBER = 0;
    public static final String RATE_SORT_PARAMETER = "rate";
    public static final String DATE_SORT_PARAMETER = "creation_date";
    public static final String NAME_SORT_PARAMETER = "name";

    @Autowired
    private VisitorCommentsAboutCustomerContractRepository repository;

    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        mySQLContainer.stop();
    }

    @Test
    @Sql("/db-scripts/visitor/comments_about_account/visitor-comments-about-account-service-it.sql")
    @Transactional
    void shouldReturnCommentsAboutAccountPageSortedByNameDESC() {
        //Given
        VisitorCommentsAboutCustomerService companyListService = new VisitorCommentsAboutCustomerService(repository);

        //When
        CommentAboutCustomerListPage companyListPage =
                companyListService.getCompanyListPage(ACCOUNT_ID, PAGE_SIZE, PAGE_NUMBER,
                                                        Sort.Direction.DESC, NAME_SORT_PARAMETER);

        //Then
        assertEquals("Wołek sp. z.ł.o", companyListPage.getComments().get(0).getAuthorName());
        assertEquals("Wołek sp. z.ł.o", companyListPage.getComments().get(1).getAuthorName());
        assertEquals("STL", companyListPage.getComments().get(2).getAuthorName());
    }

    @Test
    @Sql("/db-scripts/visitor/comments_about_account/visitor-comments-about-account-service-it.sql")
    @Transactional
    void shouldReturnCommentsAboutAccountPageSortedByDateDESC() {
        //Given
        VisitorCommentsAboutCustomerService companyListService = new VisitorCommentsAboutCustomerService(repository);

        //When
        CommentAboutCustomerListPage companyListPage =
                companyListService.getCompanyListPage(ACCOUNT_ID, PAGE_SIZE, PAGE_NUMBER,
                        Sort.Direction.DESC, DATE_SORT_PARAMETER);

        //Then
        assertEquals(Date.valueOf("2023-11-14"), companyListPage.getComments().get(0).getCreationDate());
        assertEquals(Date.valueOf("2023-11-04"), companyListPage.getComments().get(1).getCreationDate());
        assertEquals(Date.valueOf("2023-10-04"), companyListPage.getComments().get(2).getCreationDate());
    }

    @Test
    @Sql("/db-scripts/visitor/comments_about_account/visitor-comments-about-account-service-it.sql")
    @Transactional
    void shouldReturnCommentsAboutAccountPageSortedByRateASC() {
        //Given
        VisitorCommentsAboutCustomerService companyListService = new VisitorCommentsAboutCustomerService(repository);

        //When
        CommentAboutCustomerListPage companyListPage =
                companyListService.getCompanyListPage(ACCOUNT_ID, PAGE_SIZE, PAGE_NUMBER,
                        Sort.Direction.DESC, RATE_SORT_PARAMETER);

        //Then
        assertEquals(5.0, companyListPage.getComments().get(0).getRate());
        assertEquals(3.0, companyListPage.getComments().get(1).getRate());
        assertEquals(2.0, companyListPage.getComments().get(2).getRate());
    }

    @Test
    @Sql("/db-scripts/visitor/comments_about_account/visitor-comments-about-account-service-it.sql")
    @Transactional
    void shouldReturnProperPageParameters() {
        //Given
        VisitorCommentsAboutCustomerService companyListService = new VisitorCommentsAboutCustomerService(repository);

        //When
        CommentAboutCustomerListPage companyListPage =
                companyListService.getCompanyListPage(ACCOUNT_ID, PAGE_SIZE,
                                                        PAGE_NUMBER, Sort.Direction.DESC, DATE_SORT_PARAMETER);

        //Then
        assertEquals(3, companyListPage.getTotalItems());
        assertEquals(1, companyListPage.getTotalPages());
        assertEquals(0, companyListPage.getCurrentPage());
        assertEquals(10, companyListPage.getPageSize());
    }

    @Test
    @Sql("/db-scripts/visitor/comments_about_account/visitor-comments-about-account-service-it.sql")
    @Transactional
    void shouldReturnPageWithoutComments() {
        //Given
        VisitorCommentsAboutCustomerService companyListService = new VisitorCommentsAboutCustomerService(repository);

        //When
        CommentAboutCustomerListPage companyListPage =
                companyListService.getCompanyListPage(ACCOUNT_WITHOUT_COMMENTS_ID, PAGE_SIZE, PAGE_NUMBER,
                        Sort.Direction.DESC, NAME_SORT_PARAMETER);

        //Then
        assertEquals(Collections.EMPTY_LIST, companyListPage.getComments());
    }

    @Test
    @Sql("/db-scripts/visitor/comments_about_account/visitor-comments-about-account-service-it.sql")
    @Transactional
    void shouldReturnProperCommentsAboutAccountPageSortedByNameDESC() {
        //Given
        VisitorCommentsAboutCustomerService companyListService = new VisitorCommentsAboutCustomerService(repository);

        //When
        CommentAboutCustomerListPage companyListPage =
                companyListService.getCompanyListPage(ACCOUNT_ID, PAGE_SIZE, PAGE_NUMBER,
                        Sort.Direction.DESC, NAME_SORT_PARAMETER);

        //Then
        assertEquals("Wołek sp. z.ł.o", companyListPage.getComments().get(0).getAuthorName());
        assertEquals(1, companyListPage.getComments().get(0).getAuthorId());
        assertEquals(1, companyListPage.getComments().get(0).getCommentId());
        assertNull(companyListPage.getComments().get(0).getAuthorProfilePicture());
        assertEquals("Very nice and kind client :)", companyListPage.getComments().get(0).getText());
        assertEquals(5.0, companyListPage.getComments().get(0).getRate());
        assertEquals(Date.valueOf("2023-10-04"), companyListPage.getComments().get(0).getCreationDate());
        assertEquals("In progress", companyListPage.getComments().get(0).getContractStatus());
    }
}