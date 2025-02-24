package com.local_expert.backend.account_management.registration;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity(name = "registration-confirmation-code-entity")
@Table(name = "confirmation_code")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountRegistrationConfirmationCodeEntity {

    private static final int EXPIRATION_IN_TWO_DAYS = 60 * 24 * 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String confirmationCode;

    @OneToOne(targetEntity = UserAccountRegistrationAccountEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserAccountRegistrationAccountEntity user;

    @Builder.Default
    private LocalDate expiryDate = calculateExpiryDate(EXPIRATION_IN_TWO_DAYS);

    private static LocalDate calculateExpiryDate(int expiryTimeInMinutes) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        ZonedDateTime expiryDateTime = now.plusMinutes(expiryTimeInMinutes);
        return expiryDateTime.toLocalDate();
    }
}
