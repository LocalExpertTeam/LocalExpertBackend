package com.local_expert.backend.company_data.company_list;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Company {

    private long id;
    private String name;
    private String description;
    private String profile;
    private Double meanRate;
    private long rateCount;

}
