package com.ppinol.coworkingapp.core.domain.meetingRoom.reservation;

import com.ppinol.coworkingapp.core.BadRequestException;

public class InvalidMeetingRoomReservationDateException extends BadRequestException {
    public InvalidMeetingRoomReservationDateException(String message) {
        super(message);
    }
}
