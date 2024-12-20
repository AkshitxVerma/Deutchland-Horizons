package com.example.Deutschland;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/login", "/register","/js/**","/css/**","/public/**","static/**").permitAll() // Allow access to login and public URLs
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
            .anyRequest().authenticated() // All other requests require authentication
        )        
        .formLogin(form -> form
            .loginPage("/login") // Custom login page URL
            .successHandler(new CustomAuthenticationSuccessHandler()) // Redirect to /home on successful login
            .failureUrl("/login?error=true") // Redirect to login page with error query parameter on failure
            .permitAll() // Allow everyone to access the login page
        )
        .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)  // Invalidates the HTTP session
                .deleteCookies("JSESSIONID")  // Deletes the session cookie
                .permitAll()
         );
       
    	return http.build();
    }  
}
