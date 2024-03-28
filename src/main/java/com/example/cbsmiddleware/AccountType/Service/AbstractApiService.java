package com.example.cbsmiddleware.AccountType.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

public abstract class AbstractApiService {
    @Value("${fineract.api.username}")
    protected String fineractUsername;

    @Value("${fineract.api.password}")
    protected String fineractPassword;
    protected RestTemplate restTemplate = new RestTemplate();

    protected HttpHeaders createHeaders() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        String auth = fineractUsername + ":" + fineractPassword;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        headers.set("Fineract-Platform-TenantId", "default");
        return headers;
    }
}