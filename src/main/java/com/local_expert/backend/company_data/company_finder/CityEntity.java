package com.local_expert.backend.company_data.company_finder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "city")
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private ProvinceEntity province;

    @NonNull
    private String tag;
}

