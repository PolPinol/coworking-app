package com.ppinol.coworkingapp.core.domain.reservation.hotdesk;

import com.ppinol.coworkingapp.core.exceptions.ConflictException;

public class UserAlreadyHasReservationException extends ConflictException {
    public UserAlreadyHasReservationException(String message) {
        super(message);
    }
}
