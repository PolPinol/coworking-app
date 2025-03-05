package com.ppinol.coworkingapp.membership.domain.events;

import com.ppinol.coworkingapp.membership.domain.Id;

public class EventId extends Id {
    EventId(String id) {
        super(id);
    }

    public static EventId generate() {
        return new EventId(generateId());
    }
}
