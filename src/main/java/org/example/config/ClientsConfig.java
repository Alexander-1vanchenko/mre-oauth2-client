package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;

@Configuration
public class ClientsConfig {
    
    @Bean
    public ClientRegistration mainWebClient() {
        
        return ClientRegistration
            .withRegistrationId("main-client")
            .clientId("test_web_client")
            .clientName("test_web_client")
            .clientSecret("secret")
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .scope(OidcScopes.OPENID)
            .authorizationUri("http://localhost:8080/oauth2/authorize")
            .tokenUri("http://localhost:8080/oauth2/token")
            .redirectUri("http://localhost:7070/login/oauth2/code/main-client")
            .build();
    }
    
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        
        return new InMemoryClientRegistrationRepository(
            mainWebClient()
        );
    }
    
    @Bean
    public OAuth2AuthorizedClientManager oauth2AuthorizedClientManager(
        OAuth2AuthorizedClientRepository authorizedClientRepository
    ) {
        
        var clientManager =
            new DefaultOAuth2AuthorizedClientManager(
                clientRegistrationRepository(),
                authorizedClientRepository
            );
        
        clientManager
            .setAuthorizedClientProvider(
                OAuth2AuthorizedClientProviderBuilder.builder()
                    .authorizationCode()
                    .refreshToken()
                    .build()
            );
        
        return clientManager;
    }
}
