package com.example.socks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Not enough socks!")
public class NotEnoughSocksException extends RuntimeException {
    public NotEnoughSocksException() {
        super("Not enough socks!");
    }
}
