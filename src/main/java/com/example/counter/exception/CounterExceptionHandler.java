package com.example.counter.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CounterExceptionHandler {
    @ExceptionHandler(CounterException.class)
    public ResponseEntity<String> counterErrorHandler(CounterException counterException) {
        String msg = "Error ";
        log.info(msg + counterException.getMessage());
        return new ResponseEntity<>(counterException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
