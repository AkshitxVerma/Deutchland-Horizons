package com.example.Deutschland;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String redirectUrl = determineTargetUrl(authentication);
        response.sendRedirect(redirectUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        // Redirect based on roles
        return authentication.getAuthorities().stream()
                .filter(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))
                .findFirst()
                .map(grantedAuthority -> "/home") // Admin home page
                .orElse("/user/userHome"); // User home page
    }
}
