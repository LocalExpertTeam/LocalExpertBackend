package com.example.LocalExpertBackend.user.registration;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "company")
public class UserAccountRegistrationCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String ownerFirstName;

    @NonNull
    private String ownerLastName;

    @NonNull
    private String name;

    @NonNull
    private String nip;

    @NonNull
    @Builder.Default
    private String krs = "0";

    @NonNull
    @Builder.Default
    private String regon = "0";

    @NonNull
    private String contactMail;

    @OneToOne(fetch = FetchType.LAZY)
    private UserAccountRegistrationAccountEntity account;

    public static UserAccountRegistrationCompanyEntity valueOfUserDAO(UserDAO user) {

        UserAccountRegistrationCompanyEntity companyEntity =
                UserAccountRegistrationCompanyEntity.builder()
                                                    .name(user.getCompanyName())
                                                    .ownerFirstName(user.getFirstName())
                                                    .ownerLastName(user.getLastName())
                                                    .nip(user.getNip())
                                                    .contactMail(user.getMail())
                                                    .build();
        if (user.getKrs() != null) {
            companyEntity.setKrs(user.getKrs());
        }
        if (user.getRegon() != null) {
            companyEntity.setRegon(user.getRegon());
        }
        return companyEntity;
    }
}
