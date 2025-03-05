package com.ppinol.coworkingapp.membership.domain.events;

import java.util.Date;
import java.util.Map;

public record MembershipCreatedEventPayload(String userId, Date createdAt) implements EventPayload {
    @Override
    public Map<String, Object> asMap() {
        return Map.of("userId", userId, "createdAt", createdAt);
    }
}
