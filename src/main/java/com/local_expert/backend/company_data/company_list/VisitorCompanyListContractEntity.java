package com.local_expert.backend.company_data.company_list;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "contract")
public class VisitorCompanyListContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "companyCommentId")
    private VisitorCompanyListCommentCompanyEntity comment;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private VisitorCompanyListServiceEntity service;
}
