package com.twentythree.space.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(long id) {
        super(String.format("Player Not Found: %d", id));
    }

    public PlayerNotFoundException(String username) {
        super(String.format("Player Not Found: %s", username));
    }
}
