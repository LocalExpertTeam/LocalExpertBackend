package com.example.LocalExpertBackend.company.customer_details;

import com.example.LocalExpertBackend.enums.AccountType;
import com.example.LocalExpertBackend.user.authentication.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
class CompanyCustomerDetailsService {

    private CompanyCustomerDetailsCustomerRepository repository;

    public CustomerDetails getCustomerDetails(CustomUserDetails user, long customerId) {

        CompanyCustomerDetailsCustomerEntity customerEntity = repository.findById(customerId);

        if (user == null) {
            throw new AccessDeniedException("Access denied for non-logged in users.", HttpStatus.UNAUTHORIZED);
        } else if (isNonCompanyUser(user)) {
            throw new AccessDeniedException("Access denied for " + user.getAccountType() + " account.", HttpStatus.FORBIDDEN);
        }

        return getCustomerDetails(customerEntity);
    }

    private boolean isNonCompanyUser(CustomUserDetails user) {
        return !(user.getAccountType().equals(AccountType.COMPANY) ||
                user.getAccountType().equals(AccountType.COMPANY_PREMIUM));
    }

    private CustomerDetails getCustomerDetails(CompanyCustomerDetailsCustomerEntity customerEntity) {

        HashMap<Integer, Integer> ratesNumber = new HashMap<>();
        for (int i = 1; i <= 5; i ++) {
            ratesNumber.put(i, 0);
        }
        int ratesTotalNumber = 0;

        for (CompanyCustomerDetailsContractEntity contract : customerEntity.getContracts()) {
            CompanyCustomerDetailsCommentCustomerEntity comment = contract.getCustomerComment();
            if (comment != null) {
                double rate = comment.getRate();
                ratesNumber.merge((int) rate, 1, Integer::sum);
                ratesTotalNumber ++;
            }
        }

        return CustomerDetails.builder()
                .firstName(customerEntity.getFirstName())
                .lastName(customerEntity.getLastName())
                .email(customerEntity.getAccount().getMail())
                .phone(customerEntity.getPhone())
                .profile(customerEntity.getProfile())
                .creationDate(customerEntity.getCreationDate())
                .preferredContactTime(customerEntity.getPreferredContactTime())
                .preferredContactMethod(customerEntity.getPreferredContactMethod())
                .ratesNumber(ratesNumber)
                .ratesTotalNumber(ratesTotalNumber)
                .build();
    }
}
