package com.atomaszewski.application;

import com.atomaszewski.domain.service.UserDomainService;
import com.atomaszewski.domain.service.UserStatisticsDomainService;
import com.atomaszewski.infrastructure.client.ExternalUserClient;
import com.atomaszewski.infrastructure.model.UserCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class UserApplicationService {

    private final ExternalUserClient externalUserClient;
    private final UserDomainService userDomainService;
    private final UserStatisticsDomainService userStatisticsDomainService;

    @Transactional
    public UserRepresentation handleRequest(final String login) {
        final UserCommand userCommand = externalUserClient.getUserFormExternalResource(login);
        final UserRepresentation userRepresentation = userDomainService.prepareUserRepresentation(userCommand);
        try {
            userStatisticsDomainService.saveOrUpdateStatistics(userCommand.getLogin());
        } catch (ObjectOptimisticLockingFailureException e) {
            log.warn("User statistic with login: {} has already updated in concurrent transaction. Will try again...", userCommand.getLogin());
            userStatisticsDomainService.saveOrUpdateStatistics(userCommand.getLogin());
        }

        return userRepresentation;
    }
}
