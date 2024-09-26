package com.local_expert.backend.company_data.chat_list;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@AllArgsConstructor
public class ChatListPageAdapter {

    private Page<CustomerChatListContractEntity> contractPage;

    public ChatListPage getCooperatorListPage() {

        List<ChatElement> chats = new ArrayList<>();

        for (CustomerChatListContractEntity contract : contractPage.getContent()) {
            chats.add(prepareChatElement(contract));
        }

        return ChatListPage.builder()
                            .totalPages(contractPage.getTotalPages())
                            .currentPage(contractPage.getNumber())
                            .pageSize(contractPage.getSize())
                            .chats(chats)
                            .totalItems(contractPage.getTotalElements())
                            .build();
    }

    private ChatElement prepareChatElement(CustomerChatListContractEntity contract) {

        return ChatElement.builder()
                        .contractId(contract.getId())
                        .profile(contract.getCompany().getProfile())
                        .firstName(contract.getCompany().getOwnerFirstName())
                        .lastName(contract.getCompany().getOwnerLastName())
                        .status(contract.getStatus().getValue())
                        .title(contract.getTitle())
                        .lastActualisation(contract.getLastActualisation())
                        .containsNewMessage(isContainingNewMessage(contract))
                        .build();
    }

    private boolean isContainingNewMessage(CustomerChatListContractEntity contract) {
        Long accountId = contract.getCustomer()
                                .getAccount()
                                .getId();

        return contract.getMessages()
                        .stream()
                        .anyMatch(isUserViewedMessage(accountId));
    }

    private Predicate<CustomerChatListMessageEntity> isUserViewedMessage(Long accountId) {
        return message -> message.getUsersWhoDoNotView()
                                .stream()
                                .anyMatch(user -> Objects.equals(user.getId(), accountId));
    }
}
