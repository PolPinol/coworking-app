package com.ppinol.coworkingapp.core.ui;

import com.ppinol.coworkingapp.core.application.hotdesk.DuplicatedHotDeskNumberException;
import com.ppinol.coworkingapp.core.domain.hotdesk.InvalidHotDeskNumberException;
import com.ppinol.coworkingapp.core.ui.hotdesk.InvalidInputNumberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedHotDeskNumberException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatedNumber(DuplicatedHotDeskNumberException ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.CONFLICT)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidHotDeskNumberException.class)
    public ResponseEntity<ErrorResponse> handleInvalidNumber(InvalidHotDeskNumberException ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidInputNumberException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInputNumber(InvalidInputNumberException ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
    }
}
