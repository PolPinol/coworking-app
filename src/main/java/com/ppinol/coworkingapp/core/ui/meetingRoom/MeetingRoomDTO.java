package com.ppinol.coworkingapp.core.ui.meetingRoom;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MeetingRoomDTO {

    private final String name;
    private final String capacity;

    @JsonCreator
    public MeetingRoomDTO(@JsonProperty("name") String name, @JsonProperty("capacity") String capacity) {
        if (name == null || capacity == null) {
            throw new InvalidRegisterMeetingRoomInput("name and capacity should not be null");
        }

        if (name.trim().isEmpty() || capacity.trim().isEmpty()) {
            throw new InvalidRegisterMeetingRoomInput("name and capacity should not be empty");
        }

        this.name = name;
        this.capacity = capacity;
    }

    public String name() {
        return name;
    }

    public String capacity() {
        return capacity;
    }
}

