package com.example.LocalExpertBackend.company.companyPage;

import com.example.LocalExpertBackend.companyFinder.ServiceEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyPageController {
    private CompanyPageCompanyRepository companyPageCompanyRepository;
    private CompanyPageCompanyRegionsRepository companyPageCompanyRegionsRepository;
    private CompanyPagePriceListRepository companyPagePriceListRepository;
    private CompanyPageCompanyServiceRepository companyPageCompanyServiceRepository;

    private CompanyPageObject companyPageObject;

    public CompanyPageController(CompanyPageCompanyRepository companyPageCompanyRepository, CompanyPageCompanyRegionsRepository companyPageCompanyRegionsRepository, CompanyPagePriceListRepository companyPagePriceListRepository,CompanyPageCompanyServiceRepository companyPageCompanyServiceRepository){
        this.companyPageCompanyRepository=companyPageCompanyRepository;
        this.companyPageCompanyRegionsRepository=companyPageCompanyRegionsRepository;
        this.companyPagePriceListRepository=companyPagePriceListRepository;
        this.companyPageCompanyServiceRepository=companyPageCompanyServiceRepository;
    }

    @GetMapping("/company-page/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        if (companyPageCompanyRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        List<CompanyPageCompanyRegionsEntity> CompanyRegionsList = companyPageCompanyRegionsRepository.findByCompanyId(id);
        List<RegionsObject> regionsList = new ArrayList<>();
        for (CompanyPageCompanyRegionsEntity companyRegions : CompanyRegionsList.toArray(new CompanyPageCompanyRegionsEntity[0])) {
            RegionsObject regionsObject = new RegionsObject(companyRegions.getCity().getValue(), companyRegions.getCity().getProvince().getName());
            regionsList.add(regionsObject);
        }
        List<CompanyPageCompanyServiceEntity> CompanyServiceList = companyPageCompanyServiceRepository.findByCompanyId(id);
        List<String> serviceList = new ArrayList<>();
        for (CompanyPageCompanyServiceEntity companyService : CompanyServiceList.toArray(new CompanyPageCompanyServiceEntity[0])) {

            serviceList.add(companyService.getService().getName());
        }
        companyPageObject = new CompanyPageObject(companyPageCompanyRepository.findById(id).get(), regionsList, serviceList, companyPagePriceListRepository.getByCompanyId(id));

        return new ResponseEntity<>(companyPageObject, HttpStatus.OK);
    }
}
