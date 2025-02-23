package com.ppinol.coworkingapp.core.domain.meetingRoom.reservation;

import com.ppinol.coworkingapp.core.domain.Id;

public class MeetingRoomReservationId extends Id {

    public MeetingRoomReservationId(String id) {
        super(id);
    }

    public static MeetingRoomReservationId generate() {
        return new MeetingRoomReservationId(generateId());
    }
}
