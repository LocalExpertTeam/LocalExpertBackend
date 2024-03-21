package com.example.LocalExpertBackend.companyFinder;

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
    public ResponseEntity<?> getAll() {
        List<ServiceEntity> serviceList = serviceRepository.findAllByOrderByNameAsc();
        List<String> serviceNameList = new ArrayList<>();
        for (ServiceEntity service : serviceList) {
            serviceNameList.add(service.getName());
        }
        List<CityEntity> cityList = companyFinderCityRepository.findAllByOrderByValueAsc();
        List<List> cityNameProvinceList = new ArrayList<>();
        for (CityEntity city : cityList) {
            List pom = List.of(city.getValue(), city.getProvince().getName());
            cityNameProvinceList.add(pom);
        }
        List<ScopeEntity> scopeList = companyFinderScopeRepository.findAllByOrderByValueAsc();
        List<Long> scopeValueList = new ArrayList<>();
        for (ScopeEntity scope : scopeList) {
            scopeValueList.add(scope.getValue());
        }
        companyFinderObject = new CompanyFinderObject(cityNameProvinceList, serviceNameList, scopeValueList);
        return new ResponseEntity(companyFinderObject, HttpStatus.OK);

    }

}
