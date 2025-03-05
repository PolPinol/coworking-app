package com.ppinol.coworkingapp.core.application.hotdesk;

import com.ppinol.coworkingapp.core.exceptions.ConflictException;

public class NoAvailableHotDeskException extends ConflictException {
    public NoAvailableHotDeskException(String message) {
        super(message);
    }
}
