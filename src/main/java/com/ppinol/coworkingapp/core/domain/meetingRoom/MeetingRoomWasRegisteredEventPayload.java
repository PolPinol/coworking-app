package com.ppinol.coworkingapp.core.domain.meetingRoom;

import com.ppinol.coworkingapp.core.domain.EventPayload;

import java.util.Map;

public record MeetingRoomWasRegisteredEventPayload(String name, Integer capacity) implements EventPayload {
    @Override
    public Map<String, Object> asMap() {
        return Map.of("name", name, "capacity", capacity);
    }
}
