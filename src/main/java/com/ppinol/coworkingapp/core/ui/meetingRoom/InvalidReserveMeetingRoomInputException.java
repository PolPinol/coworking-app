package com.ppinol.coworkingapp.core.ui.meetingRoom;

import com.ppinol.coworkingapp.core.BadRequestException;

public class InvalidReserveMeetingRoomInputException extends BadRequestException {
    public InvalidReserveMeetingRoomInputException(String message) {
        super(message);
    }
}
