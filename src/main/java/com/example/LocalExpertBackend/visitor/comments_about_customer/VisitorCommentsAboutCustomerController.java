package com.example.LocalExpertBackend.visitor.comments_about_customer;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VisitorCommentsAboutCustomerController {

    private VisitorCommentsAboutCustomerService commentsAboutCustomerService;

    @GetMapping("/comments-about-customer/{customerId}")
    public ResponseEntity<CommentAboutCustomerListPage> getCompanyList(
                                            @PathVariable Long customerId,
                                            @RequestParam(defaultValue = "10") int pageSize,
                                            @RequestParam int pageNumber,
                                            @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection,
                                            @RequestParam(defaultValue = "date") String sortParameter) {

        CommentAboutCustomerListPage page =
                commentsAboutCustomerService.getCompanyListPage(customerId, pageSize, pageNumber, sortDirection, sortParameter);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
}
