package com.local_expert.backend.company_data.company_details;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "company_region")
public class CompanyPageCompanyRegionEntity {
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
