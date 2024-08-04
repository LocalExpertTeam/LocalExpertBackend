package com.example.LocalExpertBackend.visitor.company_list;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
public class CompanyListPage {

    private List<Company> companies;
    private int currentPage;
    private int totalPages;
    private long totalItems;
    private int pageSize;
}
