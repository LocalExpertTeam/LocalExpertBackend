package com.local_expert.backend.company_data.chat_list;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CustomerChatListController {

    private CustomerChatListService companyListService;

    @GetMapping("/chat-list/{customerId}")
    public ResponseEntity<Object> getCompanyList(@PathVariable long customerId,
                                                @RequestParam(defaultValue = "10") int pageSize,
                                                @RequestParam int pageNumber) {

        ChatListPage companyListPage = companyListService.getCompanyListPage(customerId, pageSize, pageNumber);
        return new ResponseEntity<>(companyListPage, HttpStatus.OK);
    }
}
