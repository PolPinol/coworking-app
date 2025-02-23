package com.ppinol.coworkingapp.core.domain.meetingRoom;

import com.ppinol.coworkingapp.core.BadRequestException;

public class InvalidMeetingRoomCapacityException extends BadRequestException {
    public InvalidMeetingRoomCapacityException(String message) {
        super(message);
    }
}
