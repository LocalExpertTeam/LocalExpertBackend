package com.example.LocalExpertBackend.user.authentication;

import com.example.LocalExpertBackend.exception.ApiRequestException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserAuthenticationController {

    @PostMapping("/login")
    public CurrentUser login(@RequestBody LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new ApiRequestException("Wrong request body", HttpStatus.UNAUTHORIZED);
        }

        try {
            request.login(form.getMail(), form.getPassword());
        } catch (ServletException e) {
            throw new ApiRequestException("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }

        var auth = (Authentication) request.getUserPrincipal();
        var user = (CustomUserDetails) auth.getPrincipal();
        log.info("User {} logged in.", user.getMail());

        return new CurrentUser(user.getId(), user.getMail());
    }

    @GetMapping("/current-user")
    public CurrentUser getCurrentUser(@AuthenticationPrincipal CustomUserDetails user) {
        return new CurrentUser(user.getId(), user.getMail());
    }

    public record CurrentUser(long id, String nickname) {}
}
