package com.ppinol.coworkingapp.core.domain.meetingRoom;

import com.ppinol.coworkingapp.core.exceptions.BadRequestException;

public class InvalidMeetingRoomCapacityException extends BadRequestException {
    public InvalidMeetingRoomCapacityException(String message) {
        super(message);
    }
}
