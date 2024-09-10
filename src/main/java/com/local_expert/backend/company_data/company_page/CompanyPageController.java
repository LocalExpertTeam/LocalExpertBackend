package com.local_expert.backend.company_data.company_page;

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

    @GetMapping("/company-page/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        if (companyPageCompanyRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        List<CompanyPageCompanyRegionEntity> companyRegionsList = companyPageCompanyRegionsRepository.findByCompanyId(id);
        List<RegionsObject> regionsList = new ArrayList<>();
        for (CompanyPageCompanyRegionEntity companyRegions : companyRegionsList.toArray(new CompanyPageCompanyRegionEntity[0])) {
            RegionsObject regionsObject = new RegionsObject(companyRegions.getCity().getValue(), companyRegions.getCity().getProvince().getName());
            regionsList.add(regionsObject);
        }
        List<CompanyPageCompanyServiceEntity> companyServiceList = companyPageCompanyServiceRepository.findByCompanyId(id);
        List<String> serviceList = new ArrayList<>();
        for (CompanyPageCompanyServiceEntity companyService : companyServiceList.toArray(new CompanyPageCompanyServiceEntity[0])) {

            serviceList.add(companyService.getService().getName());
        }
        companyPageObject = new CompanyPageObject(companyPageCompanyRepository.findById(id).get(), regionsList, serviceList, companyPagePriceListRepository.getByCompanyId(id));

        return new ResponseEntity<>(companyPageObject, HttpStatus.OK);
    }
}
