package com.ppinol.coworkingapp.core.domain.meetingRoom;

import com.ppinol.coworkingapp.core.domain.Id;

public class MeetingRoomId extends Id {
    public MeetingRoomId(String id) {
        super(id);
    }

    public static MeetingRoomId generate() {
        return new MeetingRoomId(generateId());
    }
}
