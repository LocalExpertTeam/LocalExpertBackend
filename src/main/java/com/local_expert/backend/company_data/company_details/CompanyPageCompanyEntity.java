package com.local_expert.backend.company_data.company_details;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.local_expert.backend.enums.ContactMethod;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "company")
public class CompanyPageCompanyEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    private String phone;

    private String profile;

    private String gallery;

    @NonNull
    private LocalDate creationDate;

    @NonNull
    private Boolean hasFreeCommute;

    @NonNull
    private String contactMail;

    @NonNull
    private Boolean isPhoneVisible;


    private String description;

    private String preferredContactTime;

    @NonNull
    @Enumerated(EnumType.STRING)
    private ContactMethod preferredContactMethod;

    @NonNull
    private Boolean hasFreePricing;

    @NonNull
    private Boolean isFreeDiagnostics;
}
