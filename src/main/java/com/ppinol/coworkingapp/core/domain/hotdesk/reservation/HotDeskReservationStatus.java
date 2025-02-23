package com.ppinol.coworkingapp.core.domain.hotdesk.reservation;

import com.ppinol.coworkingapp.core.domain.Status;

public class HotDeskReservationStatus extends Status {

    HotDeskReservationStatus(String status) {
        super(status);
    }

    public static HotDeskReservationStatus create() {
        return new HotDeskReservationStatus(ACTIVE);
    }
}
