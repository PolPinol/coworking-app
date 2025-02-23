package com.ppinol.coworkingapp.core.ui.meetingRoom;

import com.ppinol.coworkingapp.core.BadRequestException;

public class InvalidRegisterMeetingRoomInput extends BadRequestException {
    public InvalidRegisterMeetingRoomInput(String message) {
        super(message);
    }
}
