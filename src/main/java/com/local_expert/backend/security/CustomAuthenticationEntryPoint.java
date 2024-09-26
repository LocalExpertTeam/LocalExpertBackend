package com.local_expert.backend.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter()
                .write("{\"msg\":\"Access denied for non-logged in users.\"," +
                        "\"httpStatus\":\"" + HttpStatus.UNAUTHORIZED + "\"," +
                        "\"zonedDateTime\":\"" + ZonedDateTime.now(ZoneId.of("Z")) + "\"}");
    }
}
