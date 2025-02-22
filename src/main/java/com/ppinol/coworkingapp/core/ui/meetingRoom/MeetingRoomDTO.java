package com.ppinol.coworkingapp.core.ui.meetingRoom;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record MeetingRoomDTO(String name, String capacity) {

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
}

