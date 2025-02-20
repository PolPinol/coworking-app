package com.ppinol.coworkingapp.core.domain.meetingRoom;

public class MeetingRoomCapacity {

    private final int capacity;

    public MeetingRoomCapacity(String capacity) {
        try {
            this.capacity = Integer.parseInt(capacity);
        } catch (NumberFormatException e) {
            throw new InvalidMeetingRoomCapacityException("Meeting room must be a number");
        }

        if (this.capacity <= 0) {
            throw new InvalidMeetingRoomCapacityException("Meeting room must be positive");
        }
    }

    public int value() {
        return capacity;
    }
}
