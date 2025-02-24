package com.local_expert.backend.company_data.company_list;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "company")
public class VisitorCompanyListCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    private String description;

    private String profile;

    @OneToMany
    @JoinColumn(name = "companyId")
    private List<VisitorCompanyListRegionEntity> regions;

    @OneToMany
    @JoinColumn(name = "companyId")
    private List<VisitorCompanyListContractEntity> contracts;

    private LocalDate creationDate;
}

