package com.ppinol.coworkingapp.core.domain.hotdesk;

import com.ppinol.coworkingapp.core.domain.Event;
import com.ppinol.coworkingapp.core.domain.Id;

public class HotDeskWasRegisteredEvent extends Event<HotDeskWasRegisteredEventPayload> {

    private HotDeskWasRegisteredEvent(Id aggregateId, HotDeskWasRegisteredEventPayload payload) {
        super(aggregateId, "HotDeskWasRegisteredEvent", payload);
    }

    public static HotDeskWasRegisteredEvent from(HotDesk hotDesk) {
        HotDeskWasRegisteredEventPayload payload = new HotDeskWasRegisteredEventPayload(
                hotDesk.getNumber().value()
        );
        return new HotDeskWasRegisteredEvent(hotDesk.getId(), payload);
    }
}
