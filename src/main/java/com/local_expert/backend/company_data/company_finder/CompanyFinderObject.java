package com.local_expert.backend.company_data.company_finder;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CompanyFinderObject {
    private List<CityEntity> city;
    private List<ServiceEntity> service;
    private List<ScopeEntity> scope;


}
