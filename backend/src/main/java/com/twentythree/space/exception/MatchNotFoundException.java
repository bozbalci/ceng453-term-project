package com.twentythree.space.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MatchNotFoundException extends RuntimeException {
    public MatchNotFoundException(long id) {
        super(String.format("Match Not Found: %d", id));
    }

    public MatchNotFoundException() {
        super("Match Not Found");
    }
}
