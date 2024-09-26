package com.local_expert.backend.company_data.chat_list;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerChatListServiceIT {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest");

    public static final int PAGE_SIZE = 10;
    public static final int PAGE_NUMBER = 0;

    @Autowired
    private CustomerChatListContractRepository repository;

    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        mySQLContainer.stop();
    }

    @Test
    @Sql("/db-scripts/company_data/chat-list-service-it.sql")
    @Transactional
    void shouldReturnProperPageParameters() {
        //Given
        CustomerChatListService chatListService = new CustomerChatListService(repository);

        //When
        ChatListPage companyListPage =
                chatListService.getCompanyListPage(1, PAGE_SIZE, PAGE_NUMBER);

        //Then
        assertEquals(2, companyListPage.getTotalItems());
        assertEquals(1, companyListPage.getTotalPages());
        assertEquals(0, companyListPage.getCurrentPage());
        assertEquals(10, companyListPage.getPageSize());
    }

    @Test
    @Sql("/db-scripts/company_data/chat-list-service-it.sql")
    @Transactional
    void shouldReturnChatsPageWithProperParameters() {
        //Given
        CustomerChatListService companyListService = new CustomerChatListService(repository);

        //When
        ChatListPage chatListPage =
                companyListService.getCompanyListPage(1, PAGE_SIZE, PAGE_NUMBER);

        //Then
        assertEquals(getChats(), chatListPage.getChats());
    }

    @Test
    @Sql("/db-scripts/company_data/chat-list-service-it.sql")
    @Transactional
    void shouldReturnChatsPageWithProperParametersWhereIsNoMessages() {
        //Given
        CustomerChatListService companyListService = new CustomerChatListService(repository);

        //When
        ChatListPage chatListPage =
                companyListService.getCompanyListPage(2, PAGE_SIZE, PAGE_NUMBER);

        //Then
        assertEquals(getChatWithoutMessage(), chatListPage.getChats());
    }

    @Test
    @Sql("/db-scripts/company_data/chat-list-service-it.sql")
    @Transactional
    void shouldReturnChatsPageWithoutChats() {
        //Given
        CustomerChatListService companyListService = new CustomerChatListService(repository);

        //When
        ChatListPage chatListPage =
                companyListService.getCompanyListPage(3, PAGE_SIZE, PAGE_NUMBER);

        //Then
        assertEquals(Collections.EMPTY_LIST, chatListPage.getChats());
    }

    private List<ChatElement> getChats() {

        ArrayList<ChatElement> chats = new ArrayList<>();

        chats.add(ChatElement.builder()
                            .lastName("Wołek")
                            .firstName("Mateusz")
                            .lastActualisation(Date.valueOf("2023-10-08"))
                            .title("Bathroom renovation")
                            .contractId(1)
                            .status("In progress")
                            .profile(null)
                            .containsNewMessage(true)
                            .build());

        chats.add(ChatElement.builder()
                .lastName("Wołek")
                .firstName("Mateusz")
                .lastActualisation(null)
                .title("Roof renovation")
                .contractId(2)
                .status("In progress")
                .profile(null)
                .containsNewMessage(false)
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
                .status("In progress")
                .profile(null)
                .containsNewMessage(false)
                .build());

        return chats;
    }
}