package com.local_expert.backend.account_management.authentication;

import lombok.Data;

@Data
public class LoginForm {
    private String mail;
    private String password;
}