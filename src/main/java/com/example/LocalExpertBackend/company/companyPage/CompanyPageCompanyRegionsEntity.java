package com.example.LocalExpertBackend.company.companyPage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@Data
@Entity
@Table(name = "company_regions")
public class CompanyPageCompanyRegionsEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long companyId;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private CompanyPageCityEntity city;

    @ManyToOne
    @JoinColumn(name = "scope_id")
    private CompanyPageScopeEntity scope;
}
