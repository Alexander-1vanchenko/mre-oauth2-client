package org.example.controllers;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class TokenController {
    
    @GetMapping("/hello")
    public String getToken(
        @RegisteredOAuth2AuthorizedClient("main-client")
        OAuth2AuthorizedClient client
    ) {
        
        return "access token: " +
            client.getAccessToken().getTokenType() + " " +
            client.getAccessToken().getTokenValue();
    }
}
