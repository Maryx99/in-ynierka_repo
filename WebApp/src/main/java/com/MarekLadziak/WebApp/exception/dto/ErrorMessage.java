package com.MarekLadziak.WebApp.exception.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorMessage {
    private final HttpStatus httpStatus;
    private final int statusCode;
    private final LocalDateTime timeStamp;
    private final String message;

    public ErrorMessage(HttpStatus httpStatus, int statusCode, LocalDateTime timeStamp, String message) {
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
        this.message = message;
    }
}
