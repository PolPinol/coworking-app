package com.ppinol.coworkingapp.core.domain.hotdesk.reservation;

import com.ppinol.coworkingapp.core.domain.Id;

public class HotDeskReservationId extends Id {

    public HotDeskReservationId(String id) {
        super(id);
    }

    public static HotDeskReservationId generate() {
        return new HotDeskReservationId(generateId());
    }
}
