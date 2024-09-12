package com.local_expert.backend.account_management.registration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String verificationCode;
    private UserDAO user;
    private Locale locale;

    public OnRegistrationCompleteEvent(UserDAO user, String verificationCode, Locale locale) {
        super(user);

        this.user = user;
        this.verificationCode = verificationCode;
        this.locale = locale;
    }
}
