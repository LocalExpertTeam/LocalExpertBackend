package com.example.LocalExpertBackend.user.confirmation;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class UserAccountConfirmationController {

    private UserAccountConfirmationService service;

    @PatchMapping("/confirm")
    @ResponseStatus(HttpStatus.OK)
    public void registerAccount(@RequestBody UserAccountConfirmationDAO confirmationDAO) {
        service.confirmUserAccount(confirmationDAO);
    }
}
