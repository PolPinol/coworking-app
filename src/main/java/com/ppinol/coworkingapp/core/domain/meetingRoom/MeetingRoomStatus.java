package com.ppinol.coworkingapp.core.domain.meetingRoom;

import com.ppinol.coworkingapp.core.domain.Status;

public class MeetingRoomStatus extends Status {

    MeetingRoomStatus(String status) {
        super(status);
    }

    public static MeetingRoomStatus create() {
        return new MeetingRoomStatus(ACTIVE);
    }
}