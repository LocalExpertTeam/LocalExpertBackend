package com.example.LocalExpertBackend.company.company_page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

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
    private Date creationDate;

    @NonNull
    private Boolean hasFreeCommute;

    @NonNull
    private String contactMail;

    @NonNull
    private Boolean isPhoneVisible;


    private String description;

    private String preferredContactTime;

    @NonNull
    private String preferredContactMethod;

    @NonNull
    private Boolean hasFreePricing;

    @NonNull
    private Boolean isFreeDiagnostics;
}
