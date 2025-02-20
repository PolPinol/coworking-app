package com.ppinol.coworkingapp.core.ui.hotdesk;

import com.ppinol.coworkingapp.core.application.hotdesk.DuplicatedHotDeskException;
import com.ppinol.coworkingapp.core.domain.hotdesk.InvalidHotDeskNumberException;
import com.ppinol.coworkingapp.core.ui.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = RegisterHotDeskController.class)
public class HotDeskExceptionHandler {

    @ExceptionHandler(DuplicatedHotDeskException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatedNumber(DuplicatedHotDeskException ex) {
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

    @ExceptionHandler(InvalidRegisterHotDeskInputException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInputNumber(InvalidRegisterHotDeskInputException ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
    }
}
