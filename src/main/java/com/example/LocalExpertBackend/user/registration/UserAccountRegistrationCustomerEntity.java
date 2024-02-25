package com.example.LocalExpertBackend.user.registration;

import jakarta.persistence.*;
import lombok.*;

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


    @OneToOne(fetch = FetchType.LAZY)
    private UserAccountRegistrationAccountEntity account;

    public static UserAccountRegistrationCustomerEntity valueOfUserDAO(UserDAO user) {

        return UserAccountRegistrationCustomerEntity.builder()
                                                    .firstName(user.getFirstName())
                                                    .lastName(user.getLastName())
                                                    .build();
    }
}
