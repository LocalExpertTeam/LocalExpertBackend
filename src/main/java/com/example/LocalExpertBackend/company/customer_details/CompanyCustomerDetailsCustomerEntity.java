package com.example.LocalExpertBackend.company.customer_details;

import com.example.LocalExpertBackend.enums.ContactMethod;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customer")
class CompanyCustomerDetailsCustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private String phone;

    private String profile;

    private String preferredContactTime;

    @NonNull
    @Enumerated(EnumType.STRING)
    private ContactMethod preferredContactMethod;

    @NonNull
    private Date creationDate;

    @OneToOne(fetch = FetchType.LAZY)
    private CompanyCustomerDetailsAccountEntity account;

    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<CompanyCustomerDetailsContractEntity> contracts;
}
