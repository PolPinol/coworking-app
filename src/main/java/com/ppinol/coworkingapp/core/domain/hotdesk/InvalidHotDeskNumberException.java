package com.ppinol.coworkingapp.core.domain.hotdesk;

public class InvalidHotDeskNumberException extends RuntimeException {
    public InvalidHotDeskNumberException(String message) {
        super(message);
    }
}
