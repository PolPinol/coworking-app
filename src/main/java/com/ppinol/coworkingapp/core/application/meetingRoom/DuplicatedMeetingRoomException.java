package com.ppinol.coworkingapp.core.application.meetingRoom;

import com.ppinol.coworkingapp.core.exceptions.DuplicatedException;

public class DuplicatedMeetingRoomException extends DuplicatedException {
    public DuplicatedMeetingRoomException(String message) {
        super(message);
    }
}
