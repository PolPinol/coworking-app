package com.ppinol.coworkingapp.core.application.meetingRoom;

import com.ppinol.coworkingapp.core.exceptions.NotFoundException;

public class MeetingRoomNotFoundException extends NotFoundException {
    public MeetingRoomNotFoundException(String message) {
        super(message);
    }
}
