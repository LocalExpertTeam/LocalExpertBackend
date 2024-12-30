package com.local_expert.backend.company_data.company_details;

import com.local_expert.backend.company_data.company_finder.ProvinceEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "city")
public class CompanyPageCityEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private ProvinceEntity province;
}

