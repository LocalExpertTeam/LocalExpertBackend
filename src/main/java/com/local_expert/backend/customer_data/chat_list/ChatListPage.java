package com.local_expert.backend.customer_data.chat_list;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
