package com.example.LocalExpertBackend.visitor.comments_about_customer;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VisitorCommentsAboutCustomerService {

    private VisitorCommentsAboutCustomerContractRepository repository;

    public CommentAboutCustomerListPage getCompanyListPage(Long customerId, int pageSize, int pageNumber,
                                                           Sort.Direction sortDirection, String sortParameter) {
        String validatedSortParameter = switch (sortParameter) {
            case "name" -> "company_name";
            case "rate" -> "customerComment_rate";

            default -> "customerComment_addedDate";
        };

        Pageable page = PageRequest.of(pageNumber, pageSize, sortDirection, validatedSortParameter);
        Page<VisitorCommentsAboutCustomerContractEntity> contractEntities =
                repository.findAllByCustomerIdAndCustomerCommentIdIsNotNull(customerId, page);

        CommentAboutCustomerListPageAdapter commentAboutCustomerListPageAdapter =
                new CommentAboutCustomerListPageAdapter(contractEntities);
        return commentAboutCustomerListPageAdapter.getCompanyListPage();
    }
}
