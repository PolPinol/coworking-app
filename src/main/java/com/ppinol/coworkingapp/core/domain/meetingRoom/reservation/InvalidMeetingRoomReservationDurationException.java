package com.ppinol.coworkingapp.core.domain.meetingRoom.reservation;

import com.ppinol.coworkingapp.core.BadRequestException;

public class InvalidMeetingRoomReservationDurationException extends BadRequestException {
    public InvalidMeetingRoomReservationDurationException(String message) {
        super(message);
    }
}
