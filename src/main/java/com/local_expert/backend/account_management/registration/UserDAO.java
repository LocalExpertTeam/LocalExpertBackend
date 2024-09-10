package com.local_expert.backend.account_management.registration;

import com.local_expert.backend.enums.AccountType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDAO {

    private String mail;
    private String password;
    private AccountType accountType;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    private String companyName;
    private String nip;
    private String krs;
    private String regon;
}
