package com.MarekLadziak.WebApp.exception.handler;

import com.MarekLadziak.WebApp.exception.UserNotFoundException;
import com.MarekLadziak.WebApp.exception.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice // ta adnotacja sprawia ze mamy aspekt ktory nasluchuje na dane zdarzenia
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ErrorMessage( // w ten sposob mozesz zwracac swoje customowe informacje o bledzie
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage()
        ), HttpStatus.NOT_FOUND);
    }
}
