package com.ppinol.coworkingapp.core.domain.reservation.meetingRoom;

import com.ppinol.coworkingapp.core.domain.Status;

public class MeetingRoomReservationStatus {
    private Status status;

    MeetingRoomReservationStatus(Status status) {
        this.status = status;
    }

    public static MeetingRoomReservationStatus create() {
        return new MeetingRoomReservationStatus(Status.ACTIVE);
    }

    public Status value() {
        return status;
    }
}
