package com.ppinol.coworkingapp.core.domain.meetingRoom;

public class MeetingRoomStatus {
    private static final String ACTIVE = "Active";

    private final String status;

    MeetingRoomStatus(String status) {
        this.status = status;
    }

    public static MeetingRoomStatus create() {
        return new MeetingRoomStatus(ACTIVE);
    }

    public boolean isActive() {
        return status.equals(ACTIVE);
    }
}