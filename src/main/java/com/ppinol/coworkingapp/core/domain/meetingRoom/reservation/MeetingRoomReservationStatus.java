package com.ppinol.coworkingapp.core.domain.meetingRoom.reservation;

import com.ppinol.coworkingapp.core.domain.Status;

public class MeetingRoomReservationStatus extends Status {

    MeetingRoomReservationStatus(String status) {
        super(status);
    }

    public static MeetingRoomReservationStatus create() {
        return new MeetingRoomReservationStatus(ACTIVE);
    }
}
