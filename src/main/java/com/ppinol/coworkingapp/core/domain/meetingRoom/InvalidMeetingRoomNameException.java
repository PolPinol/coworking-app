package com.ppinol.coworkingapp.core.domain.meetingRoom;

import com.ppinol.coworkingapp.core.exceptions.BadRequestException;

public class InvalidMeetingRoomNameException extends BadRequestException {
    public InvalidMeetingRoomNameException(String message) {
        super(message);
    }
}
