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
        List<CityEntity> cityList = companyFinderCityRepository.findAllByOrderByNameAsc();
        List<BetterCity> betterCityList = new ArrayList<>();
        for (CityEntity city : cityList) {
            String bcString = city.getName() + ", " + city.getProvince().getName();
            BetterCity bc = new BetterCity(bcString, city.getTag());
            betterCityList.add(bc);
        }
        List<ScopeEntity> scopeList = companyFinderScopeRepository.findAllByOrderByValueAsc();
        List<BetterScope> betterScopeList = new ArrayList<>();
        for (ScopeEntity scope : scopeList) {
            BetterScope bs = new BetterScope(scope.getValue(), scope.getValue());
            betterScopeList.add(bs);
        }
        companyFinderObject = new CompanyFinderObject(betterCityList, serviceList, betterScopeList);
        return new ResponseEntity<>(companyFinderObject, HttpStatus.OK);

    }

}
