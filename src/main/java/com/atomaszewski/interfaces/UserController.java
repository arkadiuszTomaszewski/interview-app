package com.atomaszewski.interfaces;

import com.atomaszewski.application.UserApplicationService;
import com.atomaszewski.application.UserRepresentation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserApplicationService applicationService;

    @GetMapping("/{login}")
    public UserRepresentation startProcess(@PathVariable final String login) {
        log.info("Starting handle request with user login: {}", login);

        return applicationService.handleRequest(login);
    }
}
