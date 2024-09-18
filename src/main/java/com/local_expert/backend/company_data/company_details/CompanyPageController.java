package com.local_expert.backend.company_data.company_details;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CompanyPageController {
    private final CompanyPageCompanyRepository companyPageCompanyRepository;
    private final CompanyPageCompanyRegionsRepository companyPageCompanyRegionsRepository;
    private final CompanyPagePriceListRepository companyPagePriceListRepository;
    private final CompanyPageCompanyServiceRepository companyPageCompanyServiceRepository;

    @Getter
    private CompanyPageObject companyPageObject;

    public CompanyPageController(CompanyPageCompanyRepository companyPageCompanyRepository, CompanyPageCompanyRegionsRepository companyPageCompanyRegionsRepository, CompanyPagePriceListRepository companyPagePriceListRepository, CompanyPageCompanyServiceRepository companyPageCompanyServiceRepository) {
        this.companyPageCompanyRepository = companyPageCompanyRepository;
        this.companyPageCompanyRegionsRepository = companyPageCompanyRegionsRepository;
        this.companyPagePriceListRepository = companyPagePriceListRepository;
        this.companyPageCompanyServiceRepository = companyPageCompanyServiceRepository;
    }

    @GetMapping("/company-details/{companyId}")
    public ResponseEntity<Object> getById(@PathVariable Long companyId) {
        if (companyPageCompanyRepository.findById(companyId).isEmpty()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        List<CompanyPageCompanyRegionEntity> companyRegionsList = companyPageCompanyRegionsRepository.findByCompanyId(companyId);
        List<RegionsObject> regionsList = new ArrayList<>();
        for (CompanyPageCompanyRegionEntity companyRegions : companyRegionsList.toArray(new CompanyPageCompanyRegionEntity[0])) {
            RegionsObject regionsObject = new RegionsObject(companyRegions.getCity().getValue(), companyRegions.getCity().getProvince().getName());
            regionsList.add(regionsObject);
        }
        List<CompanyPageCompanyServiceEntity> companyServiceList = companyPageCompanyServiceRepository.findByCompanyId(companyId);
        List<String> serviceList = new ArrayList<>();
        for (CompanyPageCompanyServiceEntity companyService : companyServiceList.toArray(new CompanyPageCompanyServiceEntity[0])) {

            serviceList.add(companyService.getService().getName());
        }
        companyPageObject = new CompanyPageObject(companyPageCompanyRepository.findById(companyId).get(), regionsList, serviceList, companyPagePriceListRepository.getByCompanyId(companyId));

        return new ResponseEntity<>(companyPageObject, HttpStatus.OK);
    }
}
