package com.ppinol.coworkingapp.core.ui.office;

import com.ppinol.coworkingapp.core.application.office.DuplicatedOfficeException;
import com.ppinol.coworkingapp.core.domain.office.InvalidOfficeLeasePeriodException;
import com.ppinol.coworkingapp.core.domain.office.InvalidOfficeNumberException;
import com.ppinol.coworkingapp.core.domain.office.InvalidOfficeStatusException;
import com.ppinol.coworkingapp.core.ui.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = RegisterOfficeController.class)
public class OfficeExceptionHandler {

    @ExceptionHandler(InvalidRegisterOfficeInputException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInput(InvalidRegisterOfficeInputException ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidOfficeLeasePeriodException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInput(InvalidOfficeLeasePeriodException ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidOfficeNumberException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInput(InvalidOfficeNumberException ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidOfficeStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInput(InvalidOfficeStatusException ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(DuplicatedOfficeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInput(DuplicatedOfficeException ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.CONFLICT)
                .message(ex.getMessage())
                .build();
    }
}
