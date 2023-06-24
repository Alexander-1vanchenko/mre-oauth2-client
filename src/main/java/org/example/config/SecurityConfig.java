package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http.oauth2Client(withDefaults())
            .oauth2Login(oauth2Login ->
                oauth2Login.loginPage("/oauth2/authorization/main-client")
            );
        
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login/**", "/oauth2/**").permitAll()
                .anyRequest().authenticated()
            );
        
        return http.build();
    }
}
