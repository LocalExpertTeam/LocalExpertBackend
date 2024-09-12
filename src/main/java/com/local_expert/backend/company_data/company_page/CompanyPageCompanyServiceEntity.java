package com.local_expert.backend.company_data.company_page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "company_service")
public class CompanyPageCompanyServiceEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @NonNull
    private Long companyId;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private CompanyPageServiceEntity service;

}
