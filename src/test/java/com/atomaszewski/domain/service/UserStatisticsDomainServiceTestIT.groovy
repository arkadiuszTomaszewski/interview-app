package com.atomaszewski.domain.service

import com.atomaszewski.domain.model.UserStatistics
import com.atomaszewski.domain.repository.UserStatisticsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserStatisticsDomainServiceTestIT extends Specification {

    @Autowired
    private UserStatisticsRepository userStatisticsRepository

    @Autowired
    private UserStatisticsDomainService userStatisticsDomainService

    void 'should save user statistic'() {
        given:
        final String login = 'testLogin'

        when:
        userStatisticsDomainService.saveOrUpdateStatistics(login)

        then:
        final UserStatistics savedUserStatistics = userStatisticsRepository.findById(login).get()
        with(savedUserStatistics) {
            savedUserStatistics.getVersion() == 0
            savedUserStatistics.getRequestCount() == 1
            savedUserStatistics.getLogin() == login
        }
    }

    void 'should update user statistic'() {
        given:
        final String login = 'testLogin'
        final UserStatistics userStatisticsSaved = UserStatistics.builder().login(login).requestCount(1l).build()
        final UserStatistics savedUserStatistic = userStatisticsRepository.save(userStatisticsSaved)

        when:
        userStatisticsDomainService.saveOrUpdateStatistics(login)

        then:
        savedUserStatistic.getVersion() == 0
        final UserStatistics updatedUserStatistics = userStatisticsRepository.findById(login).get()
        with(updatedUserStatistics) {
            updatedUserStatistics.getVersion() == 1
            updatedUserStatistics.getRequestCount() == 2
            updatedUserStatistics.getLogin() == login
        }
    }
}
