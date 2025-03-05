package com.ppinol.coworkingapp.core.domain.reservation.meetingRoom;

import com.ppinol.coworkingapp.core.exceptions.BadRequestException;

public class InvalidMeetingRoomReservationHourException extends BadRequestException {
    public InvalidMeetingRoomReservationHourException(String message) {
        super(message);
    }
}
