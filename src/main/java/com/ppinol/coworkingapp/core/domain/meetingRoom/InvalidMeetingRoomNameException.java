package com.ppinol.coworkingapp.core.domain.meetingRoom;

public class InvalidMeetingRoomNameException extends RuntimeException {
    public InvalidMeetingRoomNameException(String message) {
        super(message);
    }
}
