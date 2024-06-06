package com.example.LocalExpertBackend.company.companyPage;

import com.example.LocalExpertBackend.companyFinder.ProvinceEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Builder
@AllArgsConstructor
@Data
@Entity
@Table(name = "city")
public class CompanyPageCityEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String value;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private ProvinceEntity province;
}

