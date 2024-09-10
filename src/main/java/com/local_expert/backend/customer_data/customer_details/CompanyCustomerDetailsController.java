package com.local_expert.backend.customer_data.customer_details;

import com.local_expert.backend.account_management.authentication.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CompanyCustomerDetailsController {

    private CompanyCustomerDetailsService customerDetailsService;

    @GetMapping("/customer-details/{customerId}")
    public ResponseEntity<Object> getCustomerDetails(@AuthenticationPrincipal CustomUserDetails user,
                                                              @PathVariable long customerId) {

        CustomerDetails customerDetails =
                customerDetailsService.getCustomerDetails(user, customerId);
        return new ResponseEntity<>(customerDetails, HttpStatus.OK);
    }
}
