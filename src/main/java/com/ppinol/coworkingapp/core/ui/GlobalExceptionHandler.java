package com.ppinol.coworkingapp.core.ui;

import com.ppinol.coworkingapp.core.exceptions.*;
import com.ppinol.coworkingapp.core.ui.hotdesk.RegisterHotDeskController;
import com.ppinol.coworkingapp.core.ui.hotdesk.ReserveHotDeskController;
import com.ppinol.coworkingapp.core.ui.meetingRoom.RegisterMeetingRoomController;
import com.ppinol.coworkingapp.core.ui.meetingRoom.ReserveMeetingRoomController;
import com.ppinol.coworkingapp.core.ui.office.RegisterOfficeController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {
        RegisterOfficeController.class,
        ReserveMeetingRoomController.class,
        RegisterMeetingRoomController.class,
        RegisterHotDeskController.class,
        ReserveHotDeskController.class
})
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatedException(DuplicatedException ex) {
        return ErrorResponse.builder()
                .status(498)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.CONFLICT)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalErrorException(InternalErrorException ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .build();
    }
}
