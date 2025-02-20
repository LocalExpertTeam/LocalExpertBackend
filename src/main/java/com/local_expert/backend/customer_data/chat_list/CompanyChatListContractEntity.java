package com.local_expert.backend.customer_data.chat_list;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "contract")
public class CompanyChatListContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CompanyChatListCustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "companyId")
    private CompanyChatListCompanyEntity company;

    @ManyToOne
    private CompanyChatListStatusEntity status;

    @OneToMany
    @JoinColumn(name = "contract_id")
    private List<CompanyChatListMessageEntity> messages;

    @OneToOne
    @JoinColumn(name = "companyCommentId")
    private CompanyChatListCommentCompanyEntity commentCompany;

    @NonNull
    private String title;

    @NonNull
    private LocalDate lastActualisation;
}
