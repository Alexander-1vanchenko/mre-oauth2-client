package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        http.oauth2Client(withDefaults())
            .oauth2Login(oauth2Login ->
                oauth2Login.loginPage("/oauth2/authorization/main-client")
            );
        
        http.authorizeHttpRequests(authorize ->
                authorize.anyRequest().authenticated()
            );
        
        return http.build();
    }
}
