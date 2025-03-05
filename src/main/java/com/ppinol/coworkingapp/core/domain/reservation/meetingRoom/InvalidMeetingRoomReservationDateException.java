package com.ppinol.coworkingapp.core.domain.reservation.meetingRoom;

import com.ppinol.coworkingapp.core.exceptions.BadRequestException;

public class InvalidMeetingRoomReservationDateException extends BadRequestException {
    public InvalidMeetingRoomReservationDateException(String message) {
        super(message);
    }
}
