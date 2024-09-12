package com.local_expert.backend.customer_data.comments_about_customer;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CommentAboutCustomerListPage {

    private List<CommentAboutCustomer> comments;
    private int currentPage;
    private int totalPages;
    private long totalItems;
    private int pageSize;
}
