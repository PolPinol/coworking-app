package com.ppinol.coworkingapp.core.domain.reservation.hotdesk;

import com.ppinol.coworkingapp.core.ConflictException;

public class OverlappingHotDeskReservationException extends ConflictException {
    public OverlappingHotDeskReservationException(String message) {
        super(message);
    }
}
