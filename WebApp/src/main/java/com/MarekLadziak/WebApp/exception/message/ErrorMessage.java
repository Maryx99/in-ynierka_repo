package com.MarekLadziak.WebApp.exception.message;

public enum ErrorMessage {

    USER_NOT_FOUND_ERROR("User with id: %s not found");


    ErrorMessage(String message) {
        this.message = message;
    }

    private final String message;

    public String getMessage() {
        return message;
    }
}
