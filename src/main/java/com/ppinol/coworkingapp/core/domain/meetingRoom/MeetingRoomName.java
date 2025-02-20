package com.ppinol.coworkingapp.core.domain.meetingRoom;

public class MeetingRoomName {

    private final String name;

    public MeetingRoomName(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidMeetingRoomNameException("Meeting room name must not be empty");
        }
        this.name = name;
    }

    public String value() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MeetingRoomName that)) return false;
        return name.equals(that.name);
    }
}
