package com.ppinol.coworkingapp.core.domain.office;

import com.ppinol.coworkingapp.core.domain.EventPayload;

import java.util.Map;

public record OfficeWasRegisteredEventPayload(Integer number, Integer leasePeriod) implements EventPayload {
    @Override
    public Map<String, Object> asMap() {
        return Map.of("number", number, "leasePeriod", leasePeriod);
    }
}
