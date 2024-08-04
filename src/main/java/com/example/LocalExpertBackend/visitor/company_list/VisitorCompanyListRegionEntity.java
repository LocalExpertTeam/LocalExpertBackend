package com.example.LocalExpertBackend.visitor.company_list;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "company_region")
public class VisitorCompanyListRegionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private VisitorCompanyListCityEntity city;

    @ManyToOne
    private VisitorCompanyListScopeEntity scope;
}
