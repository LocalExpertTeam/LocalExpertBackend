package com.local_expert.backend.account_management.confirmation;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "account")
class UserAccountConfirmationAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String mail;

    @Builder.Default
    @Column(columnDefinition = "boolean default false")
    private boolean isActive = false;
}
