package com.example.LocalExpertBackend.user.confirmation;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserAccountConfirmationDAO {

    private String mail;
    private String confirmationCode;
}
