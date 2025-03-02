package com.ppinol.coworkingapp.core.domain.reservation.hotdesk;

import com.ppinol.coworkingapp.core.domain.Status;

public class HotDeskReservationStatus {

    private Status status;

    HotDeskReservationStatus(Status status) {
        this.status = status;
    }

    public static HotDeskReservationStatus create() {
        return new HotDeskReservationStatus(Status.ACTIVE);
    }

    public Status value() {
        return status;
    }
}
