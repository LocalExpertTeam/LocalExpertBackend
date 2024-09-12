package com.local_expert.backend.account_management.authentication;

import com.local_expert.backend.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

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

    @NonNull
    private String password;

    @NonNull
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Builder.Default
    @Column(columnDefinition = "boolean default false")
    private boolean isActive = false;

    public CustomUserDetails toCustomUserDetails() {
        return CustomUserDetails.builder().id(id)
                                    .accountType(accountType)
                                    .isActive(isActive)
                                    .mail(mail)
                                    .password(password)
                                    .build();
    }
}
