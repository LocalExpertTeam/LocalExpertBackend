package com.local_expert.backend.account_management.registration;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class UserAccountRegistrationCustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private String phone;

    @NonNull
    private LocalDate creationDate;

    @OneToOne(fetch = FetchType.LAZY)
    private UserAccountRegistrationAccountEntity account;

    public static UserAccountRegistrationCustomerEntity valueOfUserDAO(UserDAO user) {

        return UserAccountRegistrationCustomerEntity.builder()
                                                    .firstName(user.getFirstName())
                                                    .lastName(user.getLastName())
                                                    .phone(user.getPhoneNumber())
                                                    .creationDate(LocalDate.now())
                                                    .build();
    }
}
