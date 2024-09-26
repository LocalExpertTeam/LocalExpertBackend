package com.local_expert.backend.company_data.chat_list;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "company")
public class CustomerChatListCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String ownerFirstName;

    @NonNull
    private String ownerLastName;

    private String profile;
}

