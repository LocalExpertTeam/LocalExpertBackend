package com.local_expert.backend.account_management.confirmation;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserAccountConfirmationDAO {

    private String mail;
    private String confirmationCode;
}
