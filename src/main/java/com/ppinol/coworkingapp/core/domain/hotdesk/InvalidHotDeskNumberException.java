package com.ppinol.coworkingapp.core.domain.hotdesk;

import com.ppinol.coworkingapp.core.BadRequestException;

public class InvalidHotDeskNumberException extends BadRequestException {
    public InvalidHotDeskNumberException(String message) {
        super(message);
    }
}
