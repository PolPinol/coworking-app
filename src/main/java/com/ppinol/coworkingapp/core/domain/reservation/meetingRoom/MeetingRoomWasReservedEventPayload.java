package com.ppinol.coworkingapp.core.domain.reservation.meetingRoom;

import com.ppinol.coworkingapp.core.domain.EventPayload;

import java.util.Map;

public record MeetingRoomWasReservedEventPayload(String meetingRoomId, String userId, java.time.LocalDate date,
                                                 Integer hour, Integer duration) implements EventPayload {
    @Override
    public Map<String, Object> asMap() {
        return Map.of("meetingRoomId", meetingRoomId,
                      "userId", userId,
                      "date", date,
                      "hour", hour,
                      "duration", duration);
    }
}
