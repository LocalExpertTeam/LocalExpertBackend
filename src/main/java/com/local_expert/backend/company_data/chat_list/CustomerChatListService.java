package com.local_expert.backend.company_data.chat_list;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerChatListService {

    private CustomerChatListContractRepository repository;

    public ChatListPage getCompanyListPage(long customerId, int pageSize, int pageNumber) {

        Pageable page =
                PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "messages_creationDate");
        Page<CustomerChatListContractEntity> companies = repository.findAllByCustomerId(customerId, page);

        ChatListPageAdapter companyListPageAdapter = new ChatListPageAdapter(companies);
        return companyListPageAdapter.getCooperatorListPage();
    }
}
