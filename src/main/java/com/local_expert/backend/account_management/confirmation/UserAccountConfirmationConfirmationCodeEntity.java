package com.local_expert.backend.account_management.confirmation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "confirmation-confirmation-code-entity")
@Table(name = "confirmation_code")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountConfirmationConfirmationCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String confirmationCode;

    @OneToOne(targetEntity = UserAccountConfirmationAccountEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserAccountConfirmationAccountEntity user;
}
