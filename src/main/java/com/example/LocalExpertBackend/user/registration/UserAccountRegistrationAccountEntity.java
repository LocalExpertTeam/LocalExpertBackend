package com.example.LocalExpertBackend.user.registration;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.time.LocalDate;

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

    private String phone;

    @NonNull
    private String password;

    @NonNull
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @NonNull
    private Date addedDate;

    static UserAccountRegistrationAccountEntity valueOfUserDAO(UserDAO userDAO) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return UserAccountRegistrationAccountEntity.builder()
                .mail(userDAO.getMail())
                .phone(userDAO.getPhoneNumber())
                .password(passwordEncoder.encode(userDAO.getPassword()))
                .accountType(userDAO.getAccountType())
                .addedDate(Date.valueOf(LocalDate.now()))
                .build();
    }
}
