package com.atomaszewski.infrastructure.client;

import com.atomaszewski.infrastructure.converter.UserCommandConverter;
import com.atomaszewski.infrastructure.model.ExternalUserMessage;
import com.atomaszewski.infrastructure.model.UserCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@AllArgsConstructor
public class ExternalUserClient {

    private static final String EXTERNAL_RESOURCE_URL = "https://api.github.com/users";
    private static final String SLASH_SEPARATOR = "/";

    private final UserCommandConverter userCommandConverter;
    private final RestTemplateBuilder restTemplateBuilder;

    public UserCommand getUserFormExternalResource(final String login) {
        final RestTemplate restTemplate = restTemplateBuilder.build();
        log.info("Fetching user with login: {}",  login);

        final String resourceURL = String.format("%s%s%s", EXTERNAL_RESOURCE_URL, SLASH_SEPARATOR, login);

        ExternalUserMessage externalUserMessage = restTemplate.getForObject(resourceURL, ExternalUserMessage.class);

        return userCommandConverter.convert(externalUserMessage);
    }

}
