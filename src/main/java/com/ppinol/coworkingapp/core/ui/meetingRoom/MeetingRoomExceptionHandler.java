package com.ppinol.coworkingapp.core.ui.meetingRoom;

import com.ppinol.coworkingapp.core.application.meetingRoom.DuplicatedMeetingRoomException;
import com.ppinol.coworkingapp.core.domain.meetingRoom.InvalidMeetingRoomCapacityException;
import com.ppinol.coworkingapp.core.domain.meetingRoom.InvalidMeetingRoomNameException;
import com.ppinol.coworkingapp.core.ui.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = RegisterMeetingRoomController.class)
public class MeetingRoomExceptionHandler {

    @ExceptionHandler(InvalidRegisterMeetingRoomInput.class)
    public ResponseEntity<ErrorResponse> handleInvalidInput(InvalidRegisterMeetingRoomInput ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidMeetingRoomCapacityException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCapacity(InvalidMeetingRoomCapacityException ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(InvalidMeetingRoomNameException.class)
    public ResponseEntity<ErrorResponse> handleInvalidName(InvalidMeetingRoomNameException ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(DuplicatedMeetingRoomException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatedMeetingRoom(DuplicatedMeetingRoomException ex) {
        return ErrorResponse.builder()
                .status(HttpStatus.CONFLICT)
                .message(ex.getMessage())
                .build();
    }
}
