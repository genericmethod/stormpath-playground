package com.genericmethod.stormpathplayground.web.model;

import com.genericmethod.stormpathplayground.web.config.StormpathPlaygroundProperties;
import com.stormpath.sdk.api.ApiKey;
import com.stormpath.sdk.api.ApiKeys;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author cfarrugia
 */
@Component
public class StormpathClient {

    @Autowired private StormpathPlaygroundProperties properties;

    private ApiKey apiKey;
    private Client client;

    public StormpathClient(){}

    @PostConstruct
    public void init(){
        apiKey = ApiKeys.builder()
                .setId(properties.getApiKey().getId())
                .setSecret(properties.getApiKey().getSecret())
                .build();

         client = Clients.builder().setApiKey(apiKey).build();
    }

    public Client getClient() {
        return client;
    }

    public ApiKey getApiKey() {
        return apiKey;
    }
}
