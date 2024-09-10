package com.local_expert.backend.customer_data.comments_about_customer;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CommentAboutCustomerListPageAdapter {

    private Page<VisitorCommentsAboutCustomerContractEntity> companyPage;

    public CommentAboutCustomerListPage getCompanyListPage() {

        List<CommentAboutCustomer> commentAboutCustomers = new ArrayList<>();

        for (VisitorCommentsAboutCustomerContractEntity contract : companyPage.getContent()) {
            commentAboutCustomers.add(prepareCompany(contract));
        }

        return CommentAboutCustomerListPage.builder()
                                            .totalPages(companyPage.getTotalPages())
                                            .currentPage(companyPage.getNumber())
                                            .pageSize(companyPage.getSize())
                                            .comments(commentAboutCustomers)
                                            .totalItems(companyPage.getTotalElements())
                                            .build();
    }

    private CommentAboutCustomer prepareCompany(VisitorCommentsAboutCustomerContractEntity contract) {

        VisitorCommentsAboutCustomerCommentCustomerEntity comment = contract.getCustomerComment();
        return CommentAboutCustomer.builder()
                                    .commentId(comment.getId())
                                    .authorId(contract.getCompany().getId())
                                    .authorName(contract.getCompany().getName())
                                    .authorProfilePicture(contract.getCompany().getProfile())
                                    .contractStatus(contract.getStatus().getValue())
                                    .creationDate(comment.getAddedDate())
                                    .rate(comment.getRate())
                                    .text(comment.getDescription())
                                    .build();

    }
}
