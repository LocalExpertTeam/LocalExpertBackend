package com.local_expert.backend.customer_data.chat_list;

import com.local_expert.backend.account_management.authentication.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CompanyChatListController {

    private CompanyChatListService customerListService;

    @GetMapping("/chat-customers-list/{companyId}")
    public ResponseEntity<Object> getCustomerList(@AuthenticationPrincipal CustomUserDetails user,
                                                  @PathVariable long companyId,
                                                  @RequestParam(defaultValue = "10") int pageSize,
                                                  @RequestParam int pageNumber) {

        ChatListPage customerListPage = customerListService.getCustomerListPage(user, companyId, pageSize, pageNumber);
        return new ResponseEntity<>(customerListPage, HttpStatus.OK);
    }
}
