package com.example.LocalExpertBackend.user.authentication;

import lombok.Data;

@Data
public class LoginForm {
    private String mail;
    private String password;
}