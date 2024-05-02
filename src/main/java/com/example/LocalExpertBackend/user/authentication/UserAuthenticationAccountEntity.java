package com.example.LocalExpertBackend.user.authentication;

import com.example.LocalExpertBackend.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "account")
public class UserAuthenticationAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String mail;

    private String phoneNumber;

    @NonNull
    private String password;

    @NonNull
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Builder.Default
    @Column(columnDefinition = "boolean default false")
    private boolean isActive = false;

    @NonNull
    private Date addedDate;

    public CustomUserDetails toCustomUserDetails() {
        return CustomUserDetails.builder().id(id)
                                    .accountType(accountType)
                                    .isActive(isActive)
                                    .phoneNumber(phoneNumber)
                                    .addedDate(addedDate)
                                    .mail(mail)
                                    .password(password)
                                    .build();
    }
}
