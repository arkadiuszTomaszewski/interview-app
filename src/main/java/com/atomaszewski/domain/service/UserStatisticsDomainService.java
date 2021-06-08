package com.atomaszewski.domain.service;

import com.atomaszewski.domain.model.UserStatistics;
import com.atomaszewski.domain.repository.UserStatisticsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class UserStatisticsDomainService {

    private final Long INITIAL_REQUEST_COUNT = 1L;

    private final UserStatisticsRepository userStatisticsRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveOrUpdateStatistics(final String login) {
        final UserStatistics userStatistics = userStatisticsRepository.findById(login)
                .map(this::updateUserStatistic)
                .orElseGet(() -> prepareNewUserStatistic(login));

        userStatisticsRepository.save(userStatistics);
    }

    private UserStatistics updateUserStatistic(final UserStatistics userStatisticsToUpdate) {
        log.info("Updated user statistics with login: {}", userStatisticsToUpdate.getLogin());
        userStatisticsToUpdate.increaseRequestCount();

        return userStatisticsToUpdate;
    }

    private UserStatistics prepareNewUserStatistic(final String login) {
        log.info("Created new user statistics with login: {}", login);

        return UserStatistics.builder()
                .login(login)
                .requestCount(INITIAL_REQUEST_COUNT)
                .build();
    }
}
