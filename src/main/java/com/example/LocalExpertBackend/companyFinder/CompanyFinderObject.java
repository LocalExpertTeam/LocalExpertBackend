package com.example.LocalExpertBackend.companyFinder;


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
    private List<List> city;
    private List<String> service;
    private List<Long> scope;


}
