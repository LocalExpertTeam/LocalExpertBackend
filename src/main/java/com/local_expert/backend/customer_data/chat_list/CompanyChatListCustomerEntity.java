package com.local_expert.backend.customer_data.chat_list;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customer")
class CompanyChatListCustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id")
    private CompanyChatListAccountEntity account;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private String profile;
}
