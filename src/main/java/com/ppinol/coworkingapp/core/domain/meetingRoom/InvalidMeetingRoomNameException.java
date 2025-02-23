package com.ppinol.coworkingapp.core.domain.meetingRoom;

import com.ppinol.coworkingapp.core.BadRequestException;

public class InvalidMeetingRoomNameException extends BadRequestException {
    public InvalidMeetingRoomNameException(String message) {
        super(message);
    }
}
