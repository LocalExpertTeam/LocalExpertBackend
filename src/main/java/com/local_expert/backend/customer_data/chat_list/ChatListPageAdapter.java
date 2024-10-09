package com.local_expert.backend.customer_data.chat_list;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@AllArgsConstructor
public class ChatListPageAdapter {

    private Page<CompanyChatListContractEntity> contractPage;

    public ChatListPage getCooperatorListPage() {

        List<ChatElement> chats = new ArrayList<>();

        for (CompanyChatListContractEntity contract : contractPage.getContent()) {
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

    private ChatElement prepareChatElement(CompanyChatListContractEntity contract) {

        return ChatElement.builder()
                        .contractId(contract.getId())
                        .profile(contract.getCustomer().getProfile())
                        .firstName(contract.getCustomer().getFirstName())
                        .lastName(contract.getCustomer().getLastName())
                        .status(contract.getStatus().getValue())
                        .title(contract.getTitle())
                        .lastActualisation(contract.getLastActualisation())
                        .containsNewMessage(isContainingNewMessage(contract))
                        .isAnswerPossible(contract.getCommentCompany() != null)
                        .isEvaluationPossible(isCooperationEnded(contract))
                        .build();
    }

    private boolean isCooperationEnded(CompanyChatListContractEntity contract) {
        return contract.getStatus().getValue().equals("Współpraca zakończona") ||
                contract.getStatus().getValue().equals("Współpraca przerwana z winy klienta") ||
                contract.getStatus().getValue().equals("Współpraca przerwana z winy kontrahenta");
    }

    private boolean isContainingNewMessage(CompanyChatListContractEntity contract) {
        Long accountId = contract.getCustomer()
                                .getAccount()
                                .getId();

        return contract.getMessages()
                        .stream()
                        .anyMatch(isUserViewedMessage(accountId));
    }

    private Predicate<CompanyChatListMessageEntity> isUserViewedMessage(Long accountId) {
        return message -> message.getUsersWhoDoNotView()
                                .stream()
                                .anyMatch(user -> Objects.equals(user.getId(), accountId));
    }
}
