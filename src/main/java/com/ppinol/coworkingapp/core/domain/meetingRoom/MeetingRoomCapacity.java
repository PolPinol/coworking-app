package com.ppinol.coworkingapp.core.domain.meetingRoom;

import com.ppinol.coworkingapp.core.domain.Number;

public class MeetingRoomCapacity extends Number {

    public MeetingRoomCapacity(int capacity) {
        super(capacity);
    }

    @Override
    public void validate(int capacity) {
        if (capacity <= 0) {
            throw new InvalidMeetingRoomCapacityException("Meeting room must be positive");
        }
    }
}
