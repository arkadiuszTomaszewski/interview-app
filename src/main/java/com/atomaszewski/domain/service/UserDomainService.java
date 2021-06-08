package com.atomaszewski.domain.service;

import com.atomaszewski.application.UserRepresentation;
import com.atomaszewski.domain.converter.UserConverter;
import com.atomaszewski.infrastructure.model.UserCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDomainService {

    private final int FIRST_MAGIC_NUMBER = 6;
    private final int SECOND_MAGIC_NUMBER = 2;

    private final UserConverter userConverter;

    public UserRepresentation prepareUserRepresentation(final UserCommand userCommand) {

        final UserRepresentation userRepresentation = userConverter.convert(userCommand);
        userRepresentation.setCalculations(computeCalculationsValue(userCommand));

        return userRepresentation;
    }

    private float computeCalculationsValue(final UserCommand userCommand) {
        if (userCommand.getFollowers() == 0) {
            return 0;
        }

        return FIRST_MAGIC_NUMBER / userCommand.getFollowers().floatValue() * (SECOND_MAGIC_NUMBER + userCommand.getPublicRepos());
    }
}
