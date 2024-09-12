package com.local_expert.backend.account_management.registration;

import com.local_expert.backend.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "account")
class UserAccountRegistrationAccountEntity {

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

    static UserAccountRegistrationAccountEntity valueOfUserDAO(UserDAO userDAO) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return UserAccountRegistrationAccountEntity.builder()
                .mail(userDAO.getMail())
                .password(passwordEncoder.encode(userDAO.getPassword()))
                .accountType(userDAO.getAccountType())
                .build();
    }
}
