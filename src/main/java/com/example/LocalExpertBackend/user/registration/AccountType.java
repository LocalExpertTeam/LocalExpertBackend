package com.example.LocalExpertBackend.user.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
enum AccountType {
    CUSTOMER,
    COMPANY,
    COMPANY_PREMIUM
}
