package com.partycall.partycallback.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        @Autowired
        private final AuthenticationProvider authenticationProvider;

        @Autowired
        private final JWTAuthenticationFilter jwtAuthFilter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http.csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
                                .authorizeHttpRequests(authz -> authz
                                                .requestMatchers("/auth/**") // Allow all requests to the authentication
                                                                             // endpoints
                                                .permitAll()
                                                .requestMatchers(HttpMethod.GET, "/events/**") // Allow all requests to
                                                                                               // the
                                                // authentication endpoints
                                                .permitAll()
                                                .anyRequest() // Any other request must be authenticated
                                                .authenticated())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Use stateless
                                                                                                        // session
                                                                                                        // management
                                )
                                .authenticationProvider(authenticationProvider) // Set custom authentication provider
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT
                                                                                                             // filter
                                                                                                             // before
                                                                                                             // username/password
                                                                                                             // authentication

                return http.build();
        }

}
