package com.example.LocalExpertBackend.user.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAuthenticationAccountRepository repository;

    @Override
    public CustomUserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        return repository.findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + mail + " not found")).toCustomUserDetails();
    }
}
