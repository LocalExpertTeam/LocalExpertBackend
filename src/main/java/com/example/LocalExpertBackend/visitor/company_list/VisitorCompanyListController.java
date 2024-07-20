package com.example.LocalExpertBackend.visitor.company_list;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VisitorCompanyListController {

    private VisitorCompanyListService companyListService;

    @GetMapping("/company-list/{city}/{scope}/{service}")
    public ResponseEntity<?> getCompanyList(@PathVariable String city, @PathVariable int scope,
                                            @PathVariable String service,
                                            @RequestParam(defaultValue = "10") int pageSize,
                                            @RequestParam int pageNumber,
                                            @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection,
                                            @RequestParam(defaultValue = "rate") String sortParameter) {

        CompanyListPage companyListPage =
                companyListService.getCompanyListPage(city, scope, service, pageSize, pageNumber, sortDirection, sortParameter);
        return new ResponseEntity<>(companyListPage, HttpStatus.OK);
    }
}
