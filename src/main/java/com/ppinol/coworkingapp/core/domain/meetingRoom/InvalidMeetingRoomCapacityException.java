package com.ppinol.coworkingapp.core.domain.meetingRoom;

public class InvalidMeetingRoomCapacityException extends RuntimeException {
    public InvalidMeetingRoomCapacityException(String message) {
        super(message);
    }
}
