package com.example.webprog.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {

        http

                // ВИМКНУТИ CSRF
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/login", "/register")
                        .permitAll()

                        .requestMatchers("/api/v2/students/add/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/api/v2/students/edit/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/api/v2/students/delete/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/api/v2/students/search")
                        .hasAnyRole("ADMIN", "MODERATOR")

                        .requestMatchers("/api/v2/students/**")
                        .hasAnyRole("USER", "MODERATOR", "ADMIN")

                        .anyRequest()
                        .authenticated()
                )

                .formLogin(form -> form

                        .loginPage("/login")

                        .loginProcessingUrl("/login")

                        .defaultSuccessUrl("/api/v2/students", true)

                        .permitAll()
                )

                .logout(logout -> logout

                        .logoutSuccessUrl("/login")

                        .permitAll()
                );

        return http.build();
    }
}
