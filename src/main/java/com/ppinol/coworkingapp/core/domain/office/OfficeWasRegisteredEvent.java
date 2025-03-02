package com.ppinol.coworkingapp.core.domain.office;

import com.ppinol.coworkingapp.core.domain.Event;
import com.ppinol.coworkingapp.core.domain.Id;

public class OfficeWasRegisteredEvent extends Event<OfficeWasRegisteredEventPayload> {

    private OfficeWasRegisteredEvent(Id aggregateId, OfficeWasRegisteredEventPayload payload) {
        super(aggregateId, "OfficeWasRegisteredEvent", payload);
    }

    public static OfficeWasRegisteredEvent from(Office office) {
        OfficeWasRegisteredEventPayload payload = new OfficeWasRegisteredEventPayload(
                office.getNumber().value(),
                office.getLeasePeriod().value()
        );
        return new OfficeWasRegisteredEvent(office.getId(), payload);
    }
}
