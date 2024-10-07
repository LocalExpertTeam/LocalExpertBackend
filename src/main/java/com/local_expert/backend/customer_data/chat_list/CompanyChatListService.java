package com.local_expert.backend.customer_data.chat_list;

import com.local_expert.backend.account_management.authentication.CustomUserDetails;
import com.local_expert.backend.customer_data.customer_details.AccessDeniedException;
import com.local_expert.backend.enums.AccountType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyChatListService {

    private CompanyChatListContractRepository repository;

    public ChatListPage getCustomerListPage(CustomUserDetails user, long companyId, int pageSize, int pageNumber) {

        if (isNonCompanyUser(user)) {
            throw new AccessDeniedException("Access denied for " + user.getAccountType() + " account.", HttpStatus.FORBIDDEN);
        }

        Pageable page =
                PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "messages_creationDate");
        Page<CompanyChatListContractEntity> companies = repository.findAllByCompanyId(companyId, page);

        ChatListPageAdapter companyListPageAdapter = new ChatListPageAdapter(companies);
        return companyListPageAdapter.getCooperatorListPage();
    }

    private boolean isNonCompanyUser(CustomUserDetails user) {
        return !(user.getAccountType().equals(AccountType.COMPANY) ||
                user.getAccountType().equals(AccountType.COMPANY_PREMIUM));
    }
}
