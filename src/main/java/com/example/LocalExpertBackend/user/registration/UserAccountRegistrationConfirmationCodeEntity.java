package com.example.LocalExpertBackend.user.registration;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

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
    private Date expiryDate = calculateExpiryDate(EXPIRATION_IN_TWO_DAYS);

    private static Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime()
                .getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime()
                .getTime());
    }
}
