package com.example.LocalExpertBackend.companyFinder;

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
    private Long id;

    @NonNull
    private String value;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private ProvinceEntity province;
}

