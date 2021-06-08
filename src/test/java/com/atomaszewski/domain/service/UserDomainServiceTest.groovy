package com.atomaszewski.domain.service

import com.atomaszewski.application.UserRepresentation
import com.atomaszewski.domain.converter.UserConverter
import com.atomaszewski.domain.converter.UserConverterImpl
import com.atomaszewski.infrastructure.model.UserCommand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(classes =  [UserConverterImpl.class])
class UserDomainServiceTest extends Specification {

    @Autowired
    private UserConverter converter

    private UserDomainService service

    void setup() {
        service = new UserDomainService(converter)
    }

    @Unroll
    void 'should return user with correct calculations value'() {
        given:
        UserCommand testCommand = UserCommand.builder()
                .id(1L)
                .followers(followers)
                .publicRepos(publicRepos)
                .build()

        when:
        final UserRepresentation result = service.prepareUserRepresentation(testCommand)

        then:
        result != null
        result.getCalculations() == exceptCalculationsValue

        where:
        followers | publicRepos | exceptCalculationsValue
        0         | 5           | 0
        3787      | 8           | 0.015843676 as Float
        22544     | 62          | 0.017033357 as Float
    }
}
