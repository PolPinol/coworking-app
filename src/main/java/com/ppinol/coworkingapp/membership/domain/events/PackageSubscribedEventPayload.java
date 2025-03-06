package com.ppinol.coworkingapp.membership.domain.events;

import java.util.Date;
import java.util.Map;

public record PackageSubscribedEventPayload(String packageId, int credits, Date startDate, Date endDate) implements EventPayload {
    @Override
    public Map<String, Object> asMap() {
        return Map.of("packageId", packageId,
                      "credits", credits,
                      "startDate", startDate,
                      "endDate", endDate);
    }
}
