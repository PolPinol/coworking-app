package com.ppinol.coworkingapp.core.ui.meetingRoom;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterMeetingRoomDTO(String name, Integer capacity) {

    @JsonCreator
    public RegisterMeetingRoomDTO(@JsonProperty("name") String name, @JsonProperty("capacity") Integer capacity) {
        if (name == null || capacity == null) {
            throw new InvalidRegisterMeetingRoomInput("name and capacity should not be null");
        }

        if (name.trim().isEmpty()) {
            throw new InvalidRegisterMeetingRoomInput("name should not be empty");
        }

        this.name = name;
        this.capacity = capacity;
    }
}

