package com.ppinol.coworkingapp.core.domain.meetingRoom.reservation;

import com.ppinol.coworkingapp.core.BadRequestException;

public class InvalidMeetingRoomReservationHourException extends BadRequestException {
    public InvalidMeetingRoomReservationHourException(String message) {
        super(message);
    }
}
