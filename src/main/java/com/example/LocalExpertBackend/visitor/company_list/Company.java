package com.example.LocalExpertBackend.visitor.company_list;

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
