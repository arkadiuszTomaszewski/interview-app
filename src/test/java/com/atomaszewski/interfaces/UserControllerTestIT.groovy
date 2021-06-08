package com.atomaszewski.interfaces


import com.atomaszewski.application.UserRepresentation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTestIT extends Specification {

    public static final String SERVICE_URL = 'http://localhost:8080/users/'

    @Autowired
    private TestRestTemplate testRestTemplate

    void 'should return 200 Http status and user representation object'() {
        given:
        final String login = 'octocat'

        when:
        ResponseEntity<UserRepresentation> responseEntity = testRestTemplate.getForEntity("${SERVICE_URL}${login}", UserRepresentation.class)

        then:
        responseEntity.getStatusCode() == HttpStatus.OK
        responseEntity.getBody().getLogin() == login
    }
}
