package com.local_expert.backend.company_data.chat_list;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
public class ChatListPage {

    private List<ChatElement> chats;
    private int currentPage;
    private int totalPages;
    private long totalItems;
    private int pageSize;
}
