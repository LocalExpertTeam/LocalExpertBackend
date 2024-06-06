package com.example.LocalExpertBackend.company.companyPage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CompanyPageObject {
    private CompanyPageCompanyEntity company;
    private List<RegionsObject> regions;
    private List<String> services;
    private List priceList;

}
