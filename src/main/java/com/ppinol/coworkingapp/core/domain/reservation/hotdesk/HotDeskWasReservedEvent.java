package com.ppinol.coworkingapp.core.domain.reservation.hotdesk;

import com.ppinol.coworkingapp.core.domain.Event;
import com.ppinol.coworkingapp.core.domain.Id;

public class HotDeskWasReservedEvent extends Event<HotDeskWasReservedEventPayload> {

    private HotDeskWasReservedEvent(Id aggregateId, HotDeskWasReservedEventPayload payload) {
        super(aggregateId, "HotDeskWasReservedEvent", payload);
    }

    public static HotDeskWasReservedEvent from(HotDeskReservation reservation) {
        HotDeskWasReservedEventPayload payload = new HotDeskWasReservedEventPayload(
                reservation.getHotDeskId().value(),
                reservation.getUserId().value(),
                reservation.getDate().value()
        );
        return new HotDeskWasReservedEvent(reservation.getReservationId(), payload);
    }
}
