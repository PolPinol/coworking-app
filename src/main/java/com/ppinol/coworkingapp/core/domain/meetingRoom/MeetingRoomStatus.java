package com.ppinol.coworkingapp.core.domain.meetingRoom;

import com.ppinol.coworkingapp.core.domain.Status;

public class MeetingRoomStatus {

    private Status status;

    MeetingRoomStatus(Status status) {
        this.status = status;
    }

    public static MeetingRoomStatus create() {
        return new MeetingRoomStatus(Status.ACTIVE);
    }

    public Status value() {
        return status;
    }
}