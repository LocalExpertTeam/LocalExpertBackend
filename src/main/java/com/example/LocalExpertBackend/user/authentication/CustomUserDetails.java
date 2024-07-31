package com.example.LocalExpertBackend.user.authentication;

import com.example.LocalExpertBackend.enums.AccountType;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
@Builder
public class CustomUserDetails implements UserDetails {

    private Long id;
    private String mail;
    private String password;
    private AccountType accountType;
    private boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return Set.of(); }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
