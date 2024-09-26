package com.local_expert.backend.company_data.chat_list;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "contract")
public class CustomerChatListContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerChatListCustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "companyId")
    private CustomerChatListCompanyEntity company;

    @ManyToOne
    private CustomerChatListStatusEntity status;

    @OneToMany
    @JoinColumn(name = "contract_id")
    private List<CustomerChatListMessageEntity> messages;

    @NonNull
    private String title;

    @NonNull
    private Date lastActualisation;
}
