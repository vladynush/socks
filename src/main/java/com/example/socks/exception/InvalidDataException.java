package com.example.socks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid data")
public class InvalidDataException extends RuntimeException {
    public InvalidDataException() {
        super("Invalid data");
    }
}
