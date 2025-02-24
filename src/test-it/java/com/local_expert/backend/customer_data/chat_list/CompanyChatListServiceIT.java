package com.local_expert.backend.customer_data.chat_list;

import com.local_expert.backend.account_management.authentication.CustomUserDetails;
import com.local_expert.backend.customer_data.customer_details.AccessDeniedException;
import com.local_expert.backend.enums.AccountType;
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
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CompanyChatListServiceIT {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest");

    public static final int PAGE_SIZE = 10;
    public static final int PAGE_NUMBER = 0;
    public static final CustomUserDetails COMPANY_USER = CustomUserDetails.builder()
            .id(1L)
            .accountType(AccountType.COMPANY)
            .build();
    public static final CustomUserDetails COMPANY_PREMIUM_USER = CustomUserDetails.builder()
            .id(1L)
            .accountType(AccountType.COMPANY_PREMIUM)
            .build();
    public static final CustomUserDetails CUSTOMER_USER = CustomUserDetails.builder()
            .id(1L)
            .accountType(AccountType.CUSTOMER)
            .build();

    @Autowired
    private CompanyChatListContractRepository repository;

    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        mySQLContainer.stop();
    }

    @Test
    @Sql("/db-scripts/customer_data/chat-list-service-it.sql")
    @Transactional
    void shouldReturnProperPageParameters() {
        //Given
        CompanyChatListService chatListService = new CompanyChatListService(repository);

        //When
        ChatListPage companyListPage =
                chatListService.getCustomerListPage(COMPANY_USER, 1, PAGE_SIZE, PAGE_NUMBER);

        //Then
        assertEquals(3, companyListPage.getTotalItems());
        assertEquals(1, companyListPage.getTotalPages());
        assertEquals(0, companyListPage.getCurrentPage());
        assertEquals(10, companyListPage.getPageSize());
    }

    @Test
    @Sql("/db-scripts/customer_data/chat-list-service-it.sql")
    @Transactional
    void shouldReturnChatsPageWithProperParameters() {
        //Given
        CompanyChatListService companyListService = new CompanyChatListService(repository);

        //When
        ChatListPage chatListPage =
                companyListService.getCustomerListPage(COMPANY_USER, 1, PAGE_SIZE, PAGE_NUMBER);

        //Then
        assertEquals(getChats(), chatListPage.getChats());
    }

    @Test
    @Sql("/db-scripts/customer_data/chat-list-service-it.sql")
    @Transactional
    void shouldReturnChatsPageWithProperParametersWhereIsNoMessages() {
        //Given
        CompanyChatListService companyListService = new CompanyChatListService(repository);

        //When
        ChatListPage chatListPage =
                companyListService.getCustomerListPage(COMPANY_PREMIUM_USER, 2, PAGE_SIZE, PAGE_NUMBER);

        //Then
        assertEquals(getChatWithoutMessage(), chatListPage.getChats());
    }

    @Test
    @Sql("/db-scripts/customer_data/chat-list-service-it.sql")
    @Transactional
    void shouldReturnChatsPageWithoutChats() {
        //Given
        CompanyChatListService companyListService = new CompanyChatListService(repository);

        //When
        ChatListPage chatListPage =
                companyListService.getCustomerListPage(COMPANY_USER, 3, PAGE_SIZE, PAGE_NUMBER);

        //Then
        assertEquals(Collections.EMPTY_LIST, chatListPage.getChats());
    }

    @Test
    @Sql("/db-scripts/customer_data/customer-details-service-it.sql")
    @Transactional
    void shouldThrowAccessDeniedExceptionWhenUserIsNotOnCompanyAccount() {
        //Given
        CompanyChatListService customerDetailsService = new CompanyChatListService(repository);

        //When
        AccessDeniedException exception =
                assertThrows(AccessDeniedException.class,
                () -> customerDetailsService.getCustomerListPage(CUSTOMER_USER, 3, PAGE_SIZE, PAGE_NUMBER));

        //Then
        assertEquals("Access denied for " + CUSTOMER_USER.getAccountType() + " account.", exception.getMessage());
        assertEquals(HttpStatus.FORBIDDEN, exception.getHttpStatusCode());
    }

    private List<ChatElement> getChats() {

        ArrayList<ChatElement> chats = new ArrayList<>();

        chats.add(ChatElement.builder()
                            .lastName("Wołek")
                            .firstName("Mateusz")
                            .lastActualisation(LocalDate.of(2023, 10, 8))
                            .title("Bathroom renovation")
                            .contractId(1)
                            .status("W trakcie")
                            .profile(null)
                            .containsNewMessage(true)
                            .isEvaluationPossible(false)
                            .isAnswerPossible(false)
                            .build());

        chats.add(ChatElement.builder()
                .lastName("Wołek")
                .firstName("Mateusz")
                .lastActualisation(null)
                .title("Roof renovation")
                .contractId(2)
                .status("Współpraca zakończona")
                .profile(null)
                .containsNewMessage(false)
                .isEvaluationPossible(true)
                .isAnswerPossible(false)
                .build());

        chats.add(ChatElement.builder()
                .lastName("Kiełb")
                .firstName("Janusz")
                .lastActualisation(null)
                .title("Fence project")
                .contractId(4)
                .status("Współpraca przerwana z winy kontrahenta")
                .profile(null)
                .containsNewMessage(false)
                .isEvaluationPossible(true)
                .isAnswerPossible(false)
                .build());
        return chats;
    }

    private List<ChatElement> getChatWithoutMessage() {

        ArrayList<ChatElement> chats = new ArrayList<>();

        chats.add(ChatElement.builder()
                .lastName("Kiełb")
                .firstName("Janusz")
                .lastActualisation(null)
                .title("Sidewalk project")
                .contractId(3)
                .status("Współpraca przerwana z winy klienta")
                .profile(null)
                .containsNewMessage(false)
                .isEvaluationPossible(true)
                .isAnswerPossible(false)
                .build());

        return chats;
    }
}