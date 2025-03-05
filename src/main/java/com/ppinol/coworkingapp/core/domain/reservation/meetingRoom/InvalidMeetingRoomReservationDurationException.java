package com.ppinol.coworkingapp.core.domain.reservation.meetingRoom;

import com.ppinol.coworkingapp.core.exceptions.BadRequestException;

public class InvalidMeetingRoomReservationDurationException extends BadRequestException {
    public InvalidMeetingRoomReservationDurationException(String message) {
        super(message);
    }
}
