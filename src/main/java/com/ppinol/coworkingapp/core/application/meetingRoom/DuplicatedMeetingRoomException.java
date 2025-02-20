package com.ppinol.coworkingapp.core.application.meetingRoom;

public class DuplicatedMeetingRoomException extends RuntimeException {
    public DuplicatedMeetingRoomException(String message) {
        super(message);
    }
}
