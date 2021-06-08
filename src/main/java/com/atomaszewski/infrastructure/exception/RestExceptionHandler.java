package com.atomaszewski.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseBody
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> handleUserNotFoundException(HttpClientErrorException.NotFound exception) {
        log.error("User login not found: {}", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User login not found");
    }

    @ResponseBody
    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> handleUserNotFoundException(HttpServerErrorException exception) {
        log.error("Can't connect to user api. {}", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Can't connect to user api.");
    }
}
