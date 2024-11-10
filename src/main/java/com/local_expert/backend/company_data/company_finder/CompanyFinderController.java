package com.local_expert.backend.company_data.company_finder;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@Getter
@Setter
public class CompanyFinderController {
    private CompanyFinderServiceRepository serviceRepository;
    private CompanyFinderCityRepository companyFinderCityRepository;
    private CompanyFinderScopeRepository companyFinderScopeRepository;
    private CompanyFinderObject companyFinderObject;

    public CompanyFinderController(CompanyFinderServiceRepository serviceRepository, CompanyFinderCityRepository companyFinderCityRepository, CompanyFinderScopeRepository companyFinderScopeRepository) {
        this.serviceRepository = serviceRepository;
        this.companyFinderCityRepository = companyFinderCityRepository;
        this.companyFinderScopeRepository = companyFinderScopeRepository;
    }

    @GetMapping("/company-finder")
    public ResponseEntity<Object> getAll() {
        List<ServiceEntity> serviceList = serviceRepository.findAllByOrderByNameAsc();
        List<CityEntity> cityList = companyFinderCityRepository.findAllByOrderByValueAsc();
        List<ScopeEntity> scopeList = companyFinderScopeRepository.findAllByOrderByValueAsc();
        companyFinderObject = new CompanyFinderObject(cityList, serviceList, scopeList);
        return new ResponseEntity<>(companyFinderObject, HttpStatus.OK);

    }

}
