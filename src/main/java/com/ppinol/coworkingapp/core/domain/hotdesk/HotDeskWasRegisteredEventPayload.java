package com.ppinol.coworkingapp.core.domain.hotdesk;

import com.ppinol.coworkingapp.core.domain.EventPayload;

import java.util.Map;

public record HotDeskWasRegisteredEventPayload(Integer number) implements EventPayload {
    @Override
    public Map<String, Object> asMap() {
        return Map.of("number", number);
    }
}
