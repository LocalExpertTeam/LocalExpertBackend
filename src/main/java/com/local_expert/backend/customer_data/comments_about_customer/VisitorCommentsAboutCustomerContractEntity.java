package com.local_expert.backend.customer_data.comments_about_customer;

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
public class VisitorCommentsAboutCustomerContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private VisitorCommentsAboutCustomerContractStatusEntity status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private VisitorCommentsAboutCustomerCustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "companyId")
    private VisitorCommentsAboutCustomerCompanyEntity company;

    @ManyToOne
    @JoinColumn(name = "customer_comment_id")
    private VisitorCommentsAboutCustomerCommentCustomerEntity customerComment;
}
