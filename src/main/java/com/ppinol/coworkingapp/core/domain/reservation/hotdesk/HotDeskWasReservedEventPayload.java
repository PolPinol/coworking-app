package com.ppinol.coworkingapp.core.domain.reservation.hotdesk;

import com.ppinol.coworkingapp.core.domain.EventPayload;

import java.time.LocalDate;
import java.util.Map;

public record HotDeskWasReservedEventPayload(String hotDeskId, String userId, LocalDate date) implements EventPayload {
    @Override
    public Map<String, Object> asMap() {
        return Map.of("hotDeskId", hotDeskId, "userId", userId, "date", date);
    }
}
