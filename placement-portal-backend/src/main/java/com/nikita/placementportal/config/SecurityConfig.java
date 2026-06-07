package com.nikita.placementportal.config;

import com.nikita.placementportal.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .cors(Customizer.withDefaults()) // IMPORTANT
                .csrf(csrf -> csrf.disable())    // disable csrf for APIs
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/auth/register", "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/jobs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/notifications/stream").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/recruiter/**").hasAnyRole("RECRUITER", "ADMIN")
                        .requestMatchers("/api/student/**").hasAnyRole("STUDENT", "ADMIN")
                        .requestMatchers("/api/notifications/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/applications/**").hasAnyRole("STUDENT", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/jobs/**").hasAnyRole("RECRUITER", "ADMIN","STUDENT")
                        .requestMatchers(HttpMethod.PUT, "/jobs/**").hasAnyRole("RECRUITER", "ADMIN","STUDENT")
                        .requestMatchers(HttpMethod.DELETE, "/jobs/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
