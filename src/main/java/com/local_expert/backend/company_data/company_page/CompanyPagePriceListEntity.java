package com.local_expert.backend.company_data.company_page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "price_list")
public class CompanyPagePriceListEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String serviceName;

    @JsonIgnore
    @NonNull
    private Long companyId;

    private Integer priceMin;

    private Integer priceMax;

}
